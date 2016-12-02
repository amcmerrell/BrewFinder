package com.amerrell.brewfinder.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.amerrell.brewfinder.R;
import com.amerrell.brewfinder.services.BreweryDBService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BreweryListActivity extends AppCompatActivity {
    public static final String TAG = BreweryListActivity.class.getSimpleName();
    private ArrayList<String> beerNames = new ArrayList<String>(Arrays.asList(
            "Westvleteren 12 (XII)",
            "Toppling Goliath Mornin’ Delight",
            "Cigar City Hunahpu’s Imperial Stout",
            "3 Fonteinen Zenne y Frontera",
            "Three Floyds Dark Lord Russian Imperial Stout",
            "Russian River Pliny the Younger",
            "AleSmith Speedway Stout",
            "Bell’s Black Note Stout",
            "Rochefort Trappistes 10",
            "AleSmith Speedway Stout"
    ));
    private ArrayList<String> beerDescriptions = new ArrayList<String>(Arrays.asList(
            "The flavour is outstanding with a nutty, yeasty aroma and taste, and hints of coconut and caramel. The exceptional taste of the beer coming out of st Sixtus Abbey is largely down to its unusual content of live yeast, which is uncommon in beer, but seems to work well. The beer also contains residual sugars, which is responsible for causing the sweet caramel flavours.",
            "A huge Imperial Stout with an explosive espresso aroma followed by strong notes of maple syrup and vanilla.",
            "In Mayan mythology, Hun Hunahpu was the father of the Mayan hero twins Hunahpu and Xbalanque. Hun Hunahpu, along with his brother Vucub Hunahpu, was tricked by the Dark Lords of the underworld and slain. Hun Hunahpu’s corpse morphed into a cacao tree, his head becoming a cacao pod, which in typically awesome mythology fashion, spit upon the hand of a young maiden named Xiquic who promptly became pregnant with the hero twins. The twins would ultimately grow up to avenge their father and uncle and defeat the Dark Lords and ascend the heavens to become the moon and sun. We brew Hunahpu’s® Imperial Stout once a year and release it only at the brewery on the second Saturday in March. We will continue to do so until the end of the cycle.",
            "Zenne y Frontera is a unique creation from master-brewer and geuze-blender Armand Debelder and maître-sommelier Andy De Brouwer. This natural millésime beer is a blend of young lambics that have been aged for another 12 months in 40 year old Oloroso and PX sherry casks. Thanks to the long maturation, the geuze-like notes of our authentic 3 Fonteinen lambic are well accentuated by the typical, fine Oloroso and Pedro Ximénez characteristics. By gently decanting the Zenne y Frontera, the aroma of this unique lambic will evolve from vividly tangy to a complex nutty and fully fruity bouquet.",
            "A demonic Russian-Style Imperial Stout brewed with coffee, Mexican vanilla, and Indian sugar, this beer defies description. Available one day a year, in April at the brewery: Dark Lord Day.",
            "Pliny the Younger, the man, was Pliny the Elder’s nephew and adopted son. They lived nearly 2,000 years ago! Pliny the Elder is our Double IPA, so we felt it was fitting to name our Triple IPA after his son. It is almost a true Triple IPA with triple the amount of hops as a regular I.P.A. That said, it is extremely difficult, time and space consuming, and very expensive to make. And that is why we don’t make it more often! This beer is very full-bodied with tons of hop character in the nose and throughout. It is also deceptively well-balanced and smooth.",
            "Jet Black, with an off-white head. Starts with a strong coffee and dark chocolate sensation, then fades to a multitude of toasty, roasty and caramel malt flavors. Clean and crisp, full- bodied. Warmth from the high alcohol content lightens up the feel. You won't fool your taste buds - this beer is HUGE!",
            "One of the most sought-after stouts in Bell's history, Black Note Stout blends the complex aromatics of Expedition Stout with the velvety smooth texture of Double Cream Stout and ages the combination in freshly retired oak bourbon barrels for months. The resulting harmony of flavors captures the finest features of all three components: malty notes of dark chocolate, espresso & dried fruits, all buoyed by the warmth and fragrance of the bourbon barrel. Aimed squarely at the stout and bourbon aficionados, Black Note makes a grand statement about the art of the dark.",
            "The top product from the Rochefort Trappist brewery. Dark color, full and very impressive taste. Strong plum, raisin, and black currant palate, with ascending notes of vinousness and other complexities.",
            "Speedway Stout’s ominous, pitch-black appearance has become a hallmark of this modern-day classic. Chocolate and roasted malts dominate the flavor, supported by notes of dark fruit, toffee, and caramel. A healthy dose of locally-roasted coffee from Ryan Bros. Coffee, Inc. added to each batch brings out the beer’s dark chocolate flavors and enhances its drinkability. Despite its intensity, Speedway Stout’s fine carbonation and creamy mouthfeel make it very smooth and surprisingly easy to drink. This beer ages very well and will continue to mature for many years to come."
    ));

    @Bind(R.id.beerListTitleView) TextView mBeerListTitleView;
    @Bind(R.id.brewListView) ListView mBrewListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brewery_list);
        ButterKnife.bind(this);

        Typeface goodDog = Typeface.createFromAsset(getAssets(), "fonts/GoodDog.ttf");
        mBeerListTitleView.setTypeface(goodDog);

        Intent intent = getIntent();
        String zipCode = intent.getStringExtra("zipCode");
        getBreweries(zipCode);

        ArrayAdapter adapter = new ArrayAdapter(BreweryListActivity.this, android.R.layout.simple_list_item_1, beerNames);
        mBrewListView.setAdapter(adapter);



        mBrewListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>adapterView, View view, int position, long l) {
                Intent intent = new Intent(BreweryListActivity.this, BreweryDetailsActivity.class);
                String beerName = (String) mBrewListView.getItemAtPosition(position);

                intent.putExtra("beerName", beerName);
                intent.putExtra("beerDescription", beerDescriptions.get(position));

                startActivity(intent);
            }
        });
    }

    private void getBreweries(String location) {
        final BreweryDBService breweryDBService = new BreweryDBService();
        breweryDBService.findBreweries(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                Log.v(TAG, jsonData);
            }
        });
    }
}
