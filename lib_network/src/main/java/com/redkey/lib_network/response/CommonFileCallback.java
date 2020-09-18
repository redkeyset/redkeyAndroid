package com.redkey.lib_network.response;

import com.redkey.lib_network.listener.DisposeDataHandle;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CommonFileCallback implements Callback {
    public CommonFileCallback(DisposeDataHandle handle) {

    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

    }
}
