package com.vpaliy.bakingapp.widget;

import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;


public class RecipeWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return null;
    }


    private class RecipeViewsFactrory implements RemoteViewsService.RemoteViewsFactory {

        @Override
        public void onCreate() {

        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public void onDataSetChanged() {

        }

        @Override
        public int getViewTypeCount() {
            return 0;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            return null;
        }


        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}
