package com.example.myactivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MahjongCalculator {
    private String wanInput = "";
    private String tiaoInput = "";
    private String tongInput = "";

    public Map<String, List<Integer>> doCalculation() {
        Map<String, List<Integer>> inutMap = formatInput(wanInput, tiaoInput, tongInput);
        return calcuWinningResult(inutMap);
    }

    public static Map<String, List<Integer>> calcuWinningResult(Map<String, List<Integer>> inputMjAtHand) {
        HashMap<String, List<Integer>> result = new HashMap<>();
        if (inputMjAtHand.size() > 2 || inputMjAtHand.size() == 0) {
            return result;
        }
        int mjSize = getMjSize(inputMjAtHand);
        if (mjSize % 3 != 1) {
            return result;
        }
        for (String type : inputMjAtHand.keySet()) {
            for (int i = 1; i <= 9 ; i++) {
                if (isWinning(inputMjAtHand, type, i)) {
                    if (result.get(type) == null) {
                        result.put(type, new ArrayList<>());
                    }
                    result.get(type).add(i);
                }
            }
        }
        return result;
    }

    private static int getMjSize(Map<String, List<Integer>> inputMjAtHand) {
        int count = 0;
        for (String type : inputMjAtHand.keySet()) {
            count += inputMjAtHand.get(type).size();
        }
        return count;
    }

    private static boolean isWinning(Map<String, List<Integer>> inputMjAtHand, String type, int incommingMj) {
        HashMap<String, List<Integer>> tempMjs = new HashMap<>();
        for (String tempType : inputMjAtHand.keySet()) {
            tempMjs.put(tempType, new ArrayList<>(inputMjAtHand.get(tempType)));
        }
        tempMjs.get(type).add(incommingMj);
        Collections.sort(tempMjs.get(type));
        if (isSevenPairs(tempMjs)) {
            return true;
        }
        return isWinningPremitive(type, tempMjs);
    }

    public static boolean isWinningPremitive(String type, Map<String, List<Integer>> tempMjs) {
        if (tempMjs.size() == 1) {
            return isValidate(tempMjs.get(type), true);
        }
        String otherType = "";
        for (String tempType : tempMjs.keySet()) {
            if (!type.equals(tempType)) {
                otherType = tempType;
            }
        }
        if (isValidate(tempMjs.get(type), true) && isValidate(tempMjs.get(otherType), false)) {
            return true;
        }
        if (isValidate(tempMjs.get(type), false) && isValidate(tempMjs.get(otherType), true)) {
            return true;
        }
        return false;
    }

    private static boolean isValidate(List<Integer> inputMj, boolean withCouple) {
        boolean[] visitedArr = new boolean[inputMj.size()];
        if (withCouple) {
            for (int i = 0; i < inputMj.size() - 1; i++) {
                if (!inputMj.get(i).equals(inputMj.get(i + 1))) {
                    continue;
                }
                visitedArr[i] = true;
                visitedArr[i+1] = true;
                if (isValidMj(inputMj, getNextIndex(-1, visitedArr), visitedArr)) {
                    visitedArr[i] = false;
                    visitedArr[i+1] = false;
                    return true;
                }
                visitedArr[i] = false;
                visitedArr[i+1] = false;
            }
            return false;
        } else {
            return isValidMj(inputMj, getNextIndex(-1, visitedArr), visitedArr);
        }
    }

    public static void printResult(Map<String, List<Integer>> input, Map<String, List<Integer>> result) {
        String inputMjStr = getMjString(input);
        if (result.size() == 0) {
            System.out.println("当前牌型\"" + inputMjStr + "\"没有听牌");
            return;
        }
        String resultMjStr = getMjString(result);
        System.out.println("当前牌型\"" + inputMjStr + "\"已经听牌， 胡" + resultMjStr);
    }

    public static String showResult(Map<String, List<Integer>> result) {
        if (result.size() == 0) {
            return "当前牌型没有听牌";
        }
        String resultMjStr = getMjString(result);
        return "当前牌型已经听牌， 胡" + resultMjStr;
    }

    public static String getMjString(Map<String, List<Integer>> mjInput) {
        StringBuilder sb = new StringBuilder();
        for (String type : mjInput.keySet()) {
            List<Integer> mjs = mjInput.get(type);
            for (int mj : mjs) {
                sb.append(mj);
            }
            sb.append(type).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static Map<String, List<Integer>> formatInput(String wanInputStr, String tiaoInputStr, String tongInputStr) {
        HashMap<String, List<Integer>> input = new HashMap<>();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(wanInputStr);
        if (matcher.find()) {
            String wanStr = matcher.group(0);
            ArrayList<Integer> wanInput = new ArrayList<>();
            for (int i = 0; i < wanStr.length(); i++) {
                wanInput.add(wanStr.charAt(i) - '0');
            }
            Collections.sort(wanInput);
            input.put("万", wanInput);
        }
        matcher = pattern.matcher(tiaoInputStr);
        if (matcher.find()) {
            String tiaoStr = matcher.group(0);
            ArrayList<Integer> tiaoInput = new ArrayList<>();
            for (int i = 0; i < tiaoStr.length(); i++) {
                tiaoInput.add(tiaoStr.charAt(i) - '0');
            }
            Collections.sort(tiaoInput);
            input.put("条", tiaoInput);
        }
        matcher = pattern.matcher(tongInputStr);
        if (matcher.find()) {
            String tongStr = matcher.group(0);
            ArrayList<Integer> tongInput = new ArrayList<>();
            for (int i = 0; i < tongStr.length(); i++) {
                tongInput.add(tongStr.charAt(i) - '0');
            }
            Collections.sort(tongInput);
            input.put("筒", tongInput);
        }
        return input;
    }

    private static boolean isValidMj(List<Integer> inputMj, int curIndex, boolean[] visitedArr) {
        if (curIndex >= inputMj.size()) {
            if (getNextIndex(-1, visitedArr) == visitedArr.length) {
                return true;
            } else {
                return false;
            }
        }
        int nextIndex = getNextIndex(curIndex, visitedArr);
        int nextNextIndex = getNextIndex(nextIndex, visitedArr);
        if (nextIndex >= inputMj.size() || nextNextIndex >= inputMj.size()) {
            return false;
        }
        if (inputMj.get(curIndex).equals(inputMj.get(nextIndex)) && inputMj.get(nextIndex).equals(inputMj.get(nextNextIndex))) {
            visitedArr[curIndex] = true;
            visitedArr[nextIndex] = true;
            visitedArr[nextNextIndex] = true;
            if (isValidMj(inputMj, getNextIndex(nextNextIndex, visitedArr), visitedArr)) {
                return true;
            }
            visitedArr[curIndex] = false;
            visitedArr[nextIndex] = false;
            visitedArr[nextNextIndex] = false;
        }
        while (nextIndex < inputMj.size() && !(inputMj.get(curIndex).equals(inputMj.get(nextIndex) - 1))) {
            nextIndex = getNextIndex(nextIndex, visitedArr);
        }
        if (nextIndex >= inputMj.size()) {
            return false;
        }
        nextNextIndex = getNextIndex(nextIndex, visitedArr);
        while (nextNextIndex < inputMj.size() && !(inputMj.get(nextIndex).equals(inputMj.get(nextNextIndex) - 1))) {
            nextNextIndex = getNextIndex(nextNextIndex, visitedArr);
        }
        if (nextNextIndex >= inputMj.size()) {
            return false;
        }
        visitedArr[curIndex] = true;
        visitedArr[nextIndex] = true;
        visitedArr[nextNextIndex] = true;
        boolean result = isValidMj(inputMj, getNextIndex(curIndex, visitedArr), visitedArr);
        visitedArr[curIndex] = false;
        visitedArr[nextIndex] = false;
        visitedArr[nextNextIndex] = false;
        return result;
    }

    private static int getNextIndex(int currentIndex, boolean[] visited) {
        for (int i = currentIndex + 1; i < visited.length; i++) {
            if (!visited[i]) {
                return i;
            }
        }
        return visited.length;
    }

    private static boolean isSevenPairs(Map<String, List<Integer>> inputMj) {
        if (getMjSize(inputMj) != 14) {
            return false;
        }
        for (String type : inputMj.keySet()) {
            List<Integer> inputArr = inputMj.get(type);
            if (inputArr.size() % 2 != 0) {
                return false;
            }
            for (int i = 0; i < inputArr.size() / 2; i++) {
                if (!inputArr.get(i * 2).equals(inputArr.get(i * 2 + 1))) {
                    return false;
                }
            }
        }
        return true;
    }


    public String getWanInput() {
        return wanInput;
    }

    public void setWanInput(String wanInput) {
        this.wanInput = wanInput;
    }

    public String getTiaoInput() {
        return tiaoInput;
    }

    public void setTiaoInput(String tiaoInput) {
        this.tiaoInput = tiaoInput;
    }

    public String getTongInput() {
        return tongInput;
    }

    public void setTongInput(String tongInput) {
        this.tongInput = tongInput;
    }
}
