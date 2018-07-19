package gcsales.ru.seminar18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mAdapter = new ImageAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        Fresco.initialize(this);

        mAdapter.setData(Arrays.asList("https://pp.userapi.com/c830509/v830509924/21713/1xIVgK5py60.jpg",
                "https://pp.userapi.com/c840338/v840338337/8a5e5/wR-C5BYCLGk.jpg",
                "https://pp.userapi.com/c846121/v846121871/60d54/jOs5WbaPfpg.jpg",
                "https://sun1-20.userapi.com/c834203/v834203504/133992/KoV-d3r7Vyo.jpg",
                "https://pp.userapi.com/c840329/v840329894/7a582/O7R3EMMkypU.jpg",
                "https://pp.userapi.com/c844417/v844417273/2be55/kLGQCnZc6V0.jpg",
                "https://pp.userapi.com/c824601/v824601288/e46b8/0FJQ6y2CaCU.jpg"
        ));
    }
}
