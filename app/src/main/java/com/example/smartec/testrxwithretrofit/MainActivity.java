package com.example.smartec.testrxwithretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.smartec.testrxwithretrofit.adapter.MyAdapter;
import com.example.smartec.testrxwithretrofit.model.Result;
import com.example.smartec.testrxwithretrofit.model.Results;
import com.example.smartec.testrxwithretrofit.remote.APIService;
import com.example.smartec.testrxwithretrofit.remote.MyRetrofit;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button loadButton,button2;
    MyAdapter myAdapter;
    List<Result> resultList;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.my_recycler_view);
        loadButton=findViewById(R.id.my_button);
        button2=findViewById(R.id.my_button2);
        progressBar=findViewById(R.id.progress_bar);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadData();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                resultList.clear();
                myAdapter=new MyAdapter(MainActivity.this,resultList);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(myAdapter);

            }
        });
    }

    private void loadData(){
        progressBar.setVisibility(View.VISIBLE);
        resultList=new ArrayList<>();
        APIService apiService= MyRetrofit.getApiService();
        Observable<Results> observable=apiService.getResultsList().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<Results>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Results results) {
                progressBar.setVisibility(View.GONE);
                List<Result> realResult=results.getResults();
                for (int i=0;i<realResult.size();i++){
                    Result result=new Result();
                    result.setTitle(realResult.get(i).getTitle());
                    result.setOverview(realResult.get(i).getOverview());
                    resultList.add(result);
                }
                myAdapter=new MyAdapter(MainActivity.this,resultList);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(myAdapter);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });



    }

}
