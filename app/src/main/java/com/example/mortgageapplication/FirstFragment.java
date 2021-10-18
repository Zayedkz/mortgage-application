package com.example.mortgageapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mortgageapplication.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get loan amount from the textView and convert it to double
                double loan = Double.parseDouble(binding.loanAmount.getText().toString());

                // get tenure amount from the textView and convert it to double
                double tenure = Double.parseDouble(binding.loanTenure.getText().toString());

                // get interest amount from the textView and convert it to double, then divide it by 12 to get the interest per month
                double interest = Double.parseDouble(binding.interestRate.getText().toString()) / 12.0;

                // variable to hold the (1-i)^n value
                double i = Math.pow((1.0 + interest / 100.0), (tenure * 12.0));
                // equation to calculate EMI (equated monthly installments)
                double emi = loan * (interest/100.0) * i / ( i - 1 );

                // after calculated EMI, set it to the textView in the interface
                binding.monthlyPayment.setText("$" + String.format("%.2f", emi));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}