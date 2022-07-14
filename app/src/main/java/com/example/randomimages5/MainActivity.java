package com.example.randomimages5;

import androidx.appcompat.app.AppCompatActivity;

import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.randomimages5.result.result1;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    int[] images = new int[] {
            R.drawable.ic_baseline_add_24,
            R.drawable.ic_baseline_heart_24,
            R.drawable.ic_baseline_local_shipping_24,
            R.drawable.ic_baseline_pedal_bike_24,
            R.drawable.ic_baseline_star_24,
            R.drawable.ic_baseline_wb_cloudy_24
    };

    ImageView[] box_be = new ImageView[6];
    ImageView[] box_bl = new ImageView[6];
    ImageView box_q1;
    TextView text_1;

    CountDownTimer cntTimer4;

    SoundPool soundPool;
    int sound1, sound2;

    result1 rt = new result1();

    public int counts4 = 0;

    int selected[] = new int[6];
    int _view[] = new int[6];
    int _location[] = new int[6];
    int k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result1 r =  new result1();
        r.setSet3_point(0);
        result1.setSet3_time(0);

        box_be[0] = findViewById(R.id.box_be_1);
        box_be[1] = findViewById(R.id.box_be_2);
        box_be[2] = findViewById(R.id.box_be_3);
        box_be[3] = findViewById(R.id.box_be_4);
        box_be[4] = findViewById(R.id.box_be_5);
        box_be[5] = findViewById(R.id.box_be_6);

        box_bl[0] = findViewById(R.id.box_bl_1);
        box_bl[1] = findViewById(R.id.box_bl_2);
        box_bl[2] = findViewById(R.id.box_bl_3);
        box_bl[3] = findViewById(R.id.box_bl_4);
        box_bl[4] = findViewById(R.id.box_bl_5);
        box_bl[5] = findViewById(R.id.box_bl_6);

        box_bl[0].setOnClickListener(this);
        box_bl[1].setOnClickListener(this);
        box_bl[2].setOnClickListener(this);
        box_bl[3].setOnClickListener(this);
        box_bl[4].setOnClickListener(this);
        box_bl[5].setOnClickListener(this);

        box_be[0].setOnClickListener(this);
        box_be[1].setOnClickListener(this);
        box_be[2].setOnClickListener(this);

        box_be[3].setOnClickListener(this);
        box_be[4].setOnClickListener(this);
        box_be[5].setOnClickListener(this);

        box_q1 = findViewById(R.id.box_q1);
        text_1 = findViewById(R.id.text_1);

//        cntTimer4 = new CountDownTimer(120000, 1000) {
//            public void onTick(long millisUntilFinished) {
//                int num = (int) (millisUntilFinished / 1000);
//
//                result1.setSet3_time(119 - num);
//            }
//            public void onFinish() {
//
//                result1.setSet3_time(120);
//            }
//        }.start();

        int minimumValue = 0;
        int maximumValue = 5;

        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        // 처음 그림을 숨긴다
        for (int i = 0; i < 6; i++) {
            box_bl[i].setVisibility(View.INVISIBLE);

        }

        // 이미지 위치 랜덤 함수 생성
        for (int i = 0; i < 6; i++) {
            selected[i] = random.nextInt(maximumValue - minimumValue + 1) + minimumValue;
            for (int j = 0; j < i; j++) {
                if (selected[i] == selected[j]) {
                    i--;
                }
            }
        }

        // 이미지 노출 순서 랜덤 함수 생성
        for (int i = 0; i < 6; i++) {
            _view[i] = random.nextInt(maximumValue - minimumValue + 1) + minimumValue;
            for (int j = 0; j < i; j++) {
                if (_view[i] == _view[j]) {
                    i--;
                }
            }
        }

        // 이미지 종류 랜덤
        for (int k = 0; k < 6; k++) {
            _location[k] = random.nextInt(maximumValue - minimumValue + 1) + minimumValue;
            for (int l = 0; l < k; l++) {
                if (_location[k] == _location[l]) {
                    k--;
                }
            }
            box_bl[k].setImageResource(images[selected[_location[k]]]);

        }

        // 랜덤이미지 노출 순서대로 보이기 및 숨기기
        for (int i = 0; i < 6; i++) {
            final Handler handler = new Handler();
            int finalI = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    box_bl[_view[finalI]].setVisibility(View.VISIBLE);
                    if ( finalI> 0 )
                        box_bl[_view[finalI - 1 ]].setVisibility(View.INVISIBLE);
                }
            }, 1000*i);
        }

        // 전체 숨기기
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 6; i++) {
                    box_bl[i].setVisibility(View.INVISIBLE);
                }

                // 문제 그림 제시
                box_q1.setVisibility(View.VISIBLE);
                box_q1.setImageResource(images[selected[k]]);
                text_1.setText("화면 중앙에 표시된 그림이 있었던 위치를 눌러 주세요.");

                // 클릭 이벤트 생성
                demon = false;
            }
        }, 6000 );
    }

    boolean demon = true;

    // 답 클릭 시
    @Override
    public void onClick(View v) {

        if (demon == true) return;

        for (int i = 0; i < 6; i++) {
            box_bl[i].setVisibility(View.INVISIBLE);
            if(v  == box_bl[i])
                box_bl[i].setVisibility(View.VISIBLE);

            if(v  == box_be[i]) {
                box_bl[i].setVisibility(View.VISIBLE);

                // 정답 부분
                if (images[selected[k]] == images[selected[_location[i]]]) {

                    text_1.setText("정답");
                    int cdt3bb = result1.getSet3_point();
                    cdt3bb = cdt3bb + 1;
                    result1.setSet3_point(cdt3bb);

                    Handler h1 = new Handler();
                    h1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < 6; i++) {
                                box_q1.setVisibility(View.INVISIBLE);
                                box_bl[i].setVisibility(View.INVISIBLE);
                                text_1.setVisibility(View.INVISIBLE);
                            }
                        }
                    }, 1000);

                    Handler h2 = new Handler();
                    h2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            text_1.setVisibility(View.VISIBLE);

                            Question(3);
                        }
                    }, 2000);

                } else {
                    text_1.setText("오답");
                }

            }
        }
    }


    // 가장 자리 6개 박스 위치 랜덤, 이미지 랜덤 보이기
    // 가운데 1개 박스 문제 이미지 랜덤
    public void Question(int num) {

        if (num == 0) {
            return;
        }
        text_1.setText("그림의 위치를 기억해 주세요.");
        for (int i = 0; i < 6; i++) {
            box_be[i].setVisibility(View.VISIBLE);
        }

        int minimumValue = 0;
        int maximumValue = 5;

        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        // 처음 그림을 숨긴다
        for (int i = 0; i < 6; i++) {
            box_bl[i].setVisibility(View.INVISIBLE);
        }

        // 이미지 위치 랜덤 함수 생성
        for (int i = 0; i < 6; i++) {
            selected[i] = random.nextInt(maximumValue - minimumValue + 1) + minimumValue;
            for (int j = 0; j < i; j++) {
                if (selected[i] == selected[j]) {
                    i--;
                }
            }
        }

        // 이미지 노출 순서 랜덤 함수 생성
        for (int i = 0; i < 6; i++) {
            _view[i] = random.nextInt(maximumValue - minimumValue + 1) + minimumValue;
            for (int j = 0; j < i; j++) {
                if (_view[i] == _view[j]) {
                    i--;
                }
            }
        }

        // 이미지 종류 랜덤
        for (int k = 0; k < 6; k++) {
            _location[k] = random.nextInt(maximumValue - minimumValue + 1) + minimumValue;
            for (int l = 0; l < k; l++) {
                if (_location[k] == _location[l]) {
                    k--;
                }
            }
            box_bl[k].setImageResource(images[selected[_location[k]]]);
        }

        // 랜덤이미지 노출 순서대로 보이기 및 숨기기
        for (int i = 0; i < 6; i++) {
            final Handler handler = new Handler();
            int finalI = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    box_bl[_view[finalI]].setVisibility(View.VISIBLE);
                    if ( finalI> 0 )
                        box_bl[_view[finalI - 1 ]].setVisibility(View.INVISIBLE);
                }
            }, 1000*i);
        }

        // 전체 숨기기
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 6; i++) {
                    box_bl[i].setVisibility(View.INVISIBLE);
                }

                // 문제 그림 제시
                box_q1.setVisibility(View.VISIBLE);
                box_q1.setImageResource(images[selected[k]]);
                text_1.setText("화면 중앙에 표시된 그림이 있었던 위치를 눌러 주세요.");


            }
        }, 6000 );

        num = num - 1;
        Log.e("재귀 카운트", num + "     카운트");
        Question(num);
    }
}
