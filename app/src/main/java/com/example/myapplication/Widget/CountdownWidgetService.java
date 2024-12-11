package com.example.myapplication.Widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class CountdownWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new CountdownWidgetDataProvider(this,intent);
    }
}
