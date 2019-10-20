package ais.app.apar;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class HomeScreenFragment extends Fragment {

    private static String txt = "Referencing: A key academic skill\n" +
            "Accurate and correct referencing is fundamental to academic writing. Academic writing requires the use of other authors to strengthen arguments and insights as well as support your own ideas. The purpose of referencing is to acknowledge the source of the other information you have used in your writing. Academic writing enables knowledge and ideas on a topic to be shared and built upon.\n" +
            "\n" +
            "Referencing acknowledges the source of the information. When you refer to another writer’s ideas in your assignment, whether you paraphrase or use a direct quotation, you must give the source. Failure to do so is considered plagiarism.\n" +
            "APA 6th is the style of referencing used by the University of Auckland Business School.\n";

    public HomeScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View hv = inflater.inflate(R.layout.fragment_home_screen, container, false);
        TextView textView = hv.findViewById(R.id.txtMain);
        textView.setText(txt);
        return hv;
    }



}

