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

public class SecondFragment extends Fragment {
    private MajoonPlayCalculator playCalculator;
    private Map<String, Map<String, List<Integer>>> result = new HashMap<>();
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        playCalculator = new MajoonPlayCalculator();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View globalView, Bundle savedInstanceState) {
        super.onViewCreated(globalView, savedInstanceState);

        globalView.findViewById(R.id.toCalcWinning).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        EditText wanInput = (EditText) globalView.findViewById(R.id.wanInputPlay);
        wanInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                playCalculator.setWanInput(wanInput.getText().toString());
            }
        });
        EditText tiaoInput = (EditText) globalView.findViewById(R.id.tiaoInputPlay);
        tiaoInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                playCalculator.setTiaoInput(tiaoInput.getText().toString());
            }
        });
        EditText tongInput = (EditText) globalView.findViewById(R.id.tongInputPlay);
        tongInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                playCalculator.setTongInput(tongInput.getText().toString());
            }
        }
        );
        Button calcBtn = (Button) globalView.findViewById(R.id.doCalcPlay);
        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result = playCalculator.doCalculation();
                String resultStr = playCalculator.showResult(result);
                TextView resultView = (TextView) globalView.findViewById(R.id.resultDisplayPlay);
                resultView.setText(resultStr);
            }
        });
    }
}