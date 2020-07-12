package com.chaostools.ansh.Myjims.MainActivityFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chaostools.ansh.Myjims.R;


public class AboutFrag extends Fragment {
    TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_about, container, false);

        text = v.findViewById(R.id.text_l);

        text.setText("JIMS has state of art campuses that are prominently spread at eight prime" +
                " locations in Delhi and NCR. Each successive year brings a new set of opportunities" +
                " and by meeting those, JIMS continues to grow and improve.\n\n JIMS Engineering " +
                "Management Technical Campus at Greater Noida is one of the Best Engineering " +
                "Colleges at Greater Noida as well as in entire Delhi & NCR. It has well-developed " +
                "campus at 6 acres of land with all modern facilities and amenities.\n\n The campus " +
                "life is congenial, harmonious & offers opportunities to the students to pursue " +
                "education and research / consultancy activities besides other co & extra-curricular " +
                "activities.");


        return v;


    }

}
