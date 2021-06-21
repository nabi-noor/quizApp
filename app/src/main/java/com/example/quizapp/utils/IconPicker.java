package com.example.quizapp.utils;

import com.example.quizapp.R;

import java.util.ArrayList;
import java.util.List;

public class IconPicker {
    List<Integer> icons = new ArrayList<Integer>();
    int inconIndex;

    public IconPicker(){
        icons.add(R.drawable.ic_iconfinder_2175866_accounting_finance_balance_chart_report_icon);
        icons.add(R.drawable.ic_iconfinder_2824447_paper_plane_school_science_toy_icon);
        icons.add(R.drawable.ic_iconfinder_2998144_eco_growth_plant_science_sprout_icon);
        icons.add(R.drawable.ic_iconfinder_3069192_education_laboratory_school_science_icon);
        icons.add(R.drawable.ic_iconfinder_3151572_math_mathematics_pi_icon);
        icons.add(R.drawable.ic_iconfinder_3957677_atom_chemistry_nuclear_physics_science_icon);

        inconIndex = 0;
    }

    public int getIcon(){
        inconIndex = (inconIndex + 1) % icons.size();
        return icons.get(inconIndex);
    }
}
