package com.example.myactivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.myactivity.MahjongCalculator.calcuWinningResult;
import static com.example.myactivity.MahjongCalculator.formatInput;
import static com.example.myactivity.MahjongCalculator.isWinningPremitive;

public class MajoonPlayCalculator {
    private String wanInput = "";
    private String tiaoInput = "";
    private String tongInput = "";

    public Map<String, Map<String, List<Integer>>> doCalculation() {
        Map<String, List<Integer>> inutMap = formatInput(wanInput, tiaoInput, tongInput);
        return calcuWinningResultPlay(inutMap);
    }

    public static  Map<String, Map<String, List<Integer>>> calcuWinningResultPlay(Map<String, List<Integer>> inputMjAtHand) {
        HashMap<String, Map<String, List<Integer>>> result = new HashMap<>();
        if (inputMjAtHand.size() == 0 || inputMjAtHand.size() > 2) {
            return result;
        }
        String oneType = "";
        for (String tempType : inputMjAtHand.keySet()) {
            oneType = tempType;
            break;
        }
        if (isWinningPremitive(oneType, inputMjAtHand)) {
            result.put("胡", new HashMap<>());
            return result;
        }
        for (String type : inputMjAtHand.keySet()) {
            List<Integer> mjs = inputMjAtHand.get(type);
            int pre = -1;
            for (Integer mj : mjs) {
                if (mj == pre) {
                    continue;
                }
                pre = mj;
                List<Integer> tempList = new ArrayList<>(mjs);
                tempList.remove(mj);
                inputMjAtHand.put(type, tempList);
                Map<String, List<Integer>> curResult = calcuWinningResult(inputMjAtHand);
                if (curResult.size() > 0) {
                    result.put(mj + type, new HashMap<>(curResult));
                }
            }
            inputMjAtHand.put(type, mjs);
        }
        return result;
    }


    public static String showResult(Map<String, Map<String, List<Integer>>> result) {
        if (result.size() == 0) {
            return "当前牌型不能完成听牌";
        }
        if (result.get("胡") != null) {
            return "当前牌型已经胡牌";
        }
        String returnStr = "当前牌型可以完成听牌\n";
        for (String mj : result.keySet()) {
            String resultMjStr = MahjongCalculator.getMjString(result.get(mj));
            returnStr += "打" + mj + ", 胡" + resultMjStr + "\n";
        }
        return returnStr;
    }


    public static void printResult(Map<String, List<Integer>> input, Map<String, Map<String, List<Integer>>> result) {
        String inputMjStr = MahjongCalculator.getMjString(input);
        if (result.get("胡") != null) {
            System.out.println("当前牌型\"" + inputMjStr + "\"已经胡牌");
            return;
        }
        if (result.size() == 0) {
            System.out.println("当前牌型\"" + inputMjStr + "\"不能完成听牌");
            return;
        }
        System.out.println("当前牌型\"" + inputMjStr + "\"可以完成听牌");
        for (String mj : result.keySet()) {
            String resultMjStr = MahjongCalculator.getMjString(result.get(mj));
            System.out.println("打" + mj + ", 胡" + resultMjStr);
        }
        System.out.println();
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
