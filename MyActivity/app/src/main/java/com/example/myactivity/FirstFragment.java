package com.example.myactivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.myactivity.MahjongCalculator.formatInput;

public class FirstFragment extends Fragment {
    private MahjongCalculator calculator;
    private Map<String, List<Integer>> result = new HashMap<>();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        calculator = new MahjongCalculator();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View globalView, Bundle savedInstanceState) {
        super.onViewCreated(globalView, savedInstanceState);

        globalView.findViewById(R.id.toCalcPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
        EditText wanInput = (EditText) globalView.findViewById(R.id.wan_input);
        wanInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                calculator.setWanInput(wanInput.getText().toString());
            }
        });
        EditText tiaoInput = (EditText) globalView.findViewById(R.id.tiao_input);
        tiaoInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                calculator.setTiaoInput(tiaoInput.getText().toString());
            }
        });
        EditText tongInput = (EditText) globalView.findViewById(R.id.tong_input);
        tongInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                calculator.setTongInput(tongInput.getText().toString());
            }
        });
        Button calcBtn = (Button) globalView.findViewById(R.id.doCalcMj);
        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MjView mjDisplay = globalView.findViewById(R.id.myMjAtHand);
                Map<String, List<Integer>> mjInput =
                        formatInput(calculator.getWanInput(), calculator.getTiaoInput(), calculator.getTongInput());
                mjDisplay.setToDrawMjs(mjInput);
                mjDisplay.invalidate();
                result = calculator.doCalculation();
                String resultStr = calculator.showResult(result);
                TextView resultView = (TextView) globalView.findViewById(R.id.resultDisplay);
                resultView.setText(resultStr);
            }
        });
    }
}