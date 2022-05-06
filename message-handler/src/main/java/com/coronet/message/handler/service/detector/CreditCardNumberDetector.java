package com.coronet.message.handler.service.detector;

import com.coronet.dto.MessageDto;
import com.coronet.message.handler.model.detector.DetectionInfo;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreditCardNumberDetector implements Detector {

    @Override
    public Set<String> detect(MessageDto messageDto) {

        Set<String> detections = new HashSet<>();
        detections.addAll(scan(messageDto.getBody()));
        detections.addAll(scan(messageDto.getSubject()));

        return detections;
    }

    private Set<String> scan(String content) {
        if (StringUtils.isNotEmpty(content)) {
            String[] words = content.split(" ");

            return Arrays.stream(words)
                    .map(this::detectCreditCardNumber)
                    .filter(DetectionInfo::isDetected)
                    .map(DetectionInfo::getDetection)
                    .collect(Collectors.toSet());
        }

        return Sets.newHashSet();
    }

    private DetectionInfo detectCreditCardNumber(String word) {
        String cleanedWord = word.replace("-", "");

        StringBuilder creditCardStringBuilder = new StringBuilder();

        for (Character ch : cleanedWord.toCharArray()) {
            if (Character.isDigit(ch)) {
                creditCardStringBuilder.append(ch);
            } else if (creditCardStringBuilder.length() > 0) {
                // check is numbers bigger than 13 and smaller than 16 digits
                if (isCreditCard(creditCardStringBuilder.toString())) {
                    return DetectionInfo.builder()
                            .detected(true)
                            .detection(creditCardStringBuilder.toString())
                            .build();
                }

                creditCardStringBuilder = new StringBuilder();
            }
        }

        // check is numbers bigger 13 and smaller than 16 digits
        if (isCreditCard(creditCardStringBuilder.toString())) {
            return DetectionInfo.builder()
                    .detected(true)
                    .detection(creditCardStringBuilder.toString())
                    .build();
        }

        return DetectionInfo.builder()
                .detected(false)
                .build();
    }

    private boolean isCreditCard(String potentialCreditCard) {
        return potentialCreditCard.length() >= 13
                && potentialCreditCard.length() <= 16
                && validateCreditCardNumber(potentialCreditCard);
    }

    // Return true if the card number is valid
    public static boolean validateCreditCardNumber(String number) {
        if (!checkPrefix(number)) return false;

        int[] creditcardInt = new int[number.length()];

        for (int i = 0; i < number.length(); i++) {
            creditcardInt[i] = Integer.parseInt(number.charAt(i) + "");
        }

        for (int i = creditcardInt.length - 2; i >= 0; i = i - 2) {
            int tempVal = creditcardInt[i];
            tempVal *= 2;
            if (tempVal > 9) tempVal = tempVal % 10 + 1;

            creditcardInt[i] = tempVal;
        }

        //add all digits
        int total = 0;
        for (int num : creditcardInt) {
            total += num;
        }

        return total % 10 == 0;
    }

    /**
     * 4 for Visa cards
     * 5 for Master cards
     * 37 for American Express cards
     * 6 for Discover cards
     */
    private static boolean checkPrefix(String creditcardNumberString) {
        String[] validPrefix = new String[]{"4", "5", "6", "37"};
        for (var item : validPrefix) {
            if (creditcardNumberString.startsWith(item)) return true;
        }

        return false;
    }
}
