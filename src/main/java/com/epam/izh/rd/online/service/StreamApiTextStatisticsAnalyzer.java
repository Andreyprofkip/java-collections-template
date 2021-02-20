package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.*;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    private Object Integer;

    @Override
    public int countSumLengthOfWords(String text) {
        Collection<String> words = getWords(text);
        return words.stream().mapToInt(String::length).sum();
    }

    @Override
    public int countNumberOfWords(String text) {
        return getWords(text).size();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return getUniqueWords(text).size();
    }

    @Override
    public List<String> getWords(String text) {
        Collection<String> collection = Arrays.asList(text.split("\\W+"));
        List<String> spisok = collection.stream().collect(Collectors.toList());

        return spisok;
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        Collection<String> uniqueWords = getWords(text);
        return uniqueWords.stream().collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        return Arrays.stream(text.split("\\W+"))
                .collect(Collectors.groupingBy(String::valueOf, Collectors.collectingAndThen(Collectors.counting(),Long::intValue)));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        List <String> words = new ArrayList<>(getWords(text));
        if (direction == Direction.ASC) {
            words = words.stream().sorted((o1, o2) -> o1.length() - o2.length()).collect(Collectors.toList());
        }
        if (direction == Direction.DESC) {
            words = words.stream().sorted((o1, o2) -> o2.length() - o1.length()).collect(Collectors.toList());
        }
        return words;
    }
}
