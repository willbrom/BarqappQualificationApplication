package com.example.barqappqualificationapplication;

import android.app.Application;

import com.apollographql.apollo.ApolloClient;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BarqappApplication extends Application {
    private static final String BASE_URL = "http://mucho-backend.herokuapp.com/graphql";
    private ApolloClient apolloClient;

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request.Builder builder = request.newBuilder().method(request.method(), request.body());
                        builder.header("mobile", "96170692232");
                        builder.header("password", "1111");
                        return chain.proceed(builder.build());
                    }})
                .build();

        apolloClient = ApolloClient.builder()
                .serverUrl(BASE_URL)
                .okHttpClient(okHttpClient)
                .build();
    }

    public ApolloClient getApolloClient() {
        return apolloClient;
    }
}
