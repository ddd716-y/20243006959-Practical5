package com.example.practicalfive;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Municipality extends AppCompatActivity {

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        TextView textName = findViewById(R.id.textMunicipalityName);
        ImageView imageMunicipality = findViewById(R.id.imageMunicipality);
        TextView textInformation = findViewById(R.id.textInformation);

        // Retrieve and re-validate the name passed from MainActivity.
        String rawName = getIntent().getStringExtra(MainActivity.EXTRA_MUNICIPALITY);
        name = TextProcessor.processMunicipality(rawName);

        if (name == null) {
            // Missing or invalid data: fall back safely and do not crash.
            name = "Beijing";
        }

        int colourResource;
        int imageResource;
        switch (name) {
            case "Shanghai":
                colourResource = R.color.municipality_shanghai;
                imageResource = R.drawable.shanghai;
                break;
            case "Tianjin":
                colourResource = R.color.municipality_tianjin;
                imageResource = R.drawable.tianjin;
                break;
            case "Chongqing":
                colourResource = R.color.municipality_chongqing;
                imageResource = R.drawable.chongqing;
                break;
            case "Beijing":
            default:
                colourResource = R.color.municipality_beijing;
                imageResource = R.drawable.beijing;
                break;
        }

        int colour = ContextCompat.getColor(this, colourResource);

        // Municipality name on a coloured background with contrasting light text.
        textName.setText(name);
        textName.setBackgroundColor(colour);

        // Corresponding municipality image.
        imageMunicipality.setImageResource(imageResource);

        // Information summary from the string array, in the matching colour.
        String[] summaries = getResources().getStringArray(R.array.municipalities_array);
        String summary = findSummary(summaries);
        textInformation.setText(summary);
        textInformation.setTextColor(colour);
    }

    /**
     * Finds the summary for the current municipality. The array is ordered
     * Beijing, Shanghai, Chongqing, Tianjin (as defined in arrays.xml), so the
     * correct item is selected by index.
     */
    private String findSummary(String[] summaries) {
        switch (name) {
            case "Shanghai":
                return summaries[1];
            case "Chongqing":
                return summaries[2];
            case "Tianjin":
                return summaries[3];
            case "Beijing":
            default:
                return summaries[0];
        }
    }
}
