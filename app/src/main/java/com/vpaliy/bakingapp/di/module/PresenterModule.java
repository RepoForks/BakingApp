package com.vpaliy.bakingapp.di.module;

import android.content.Context;
import com.vpaliy.bakingapp.media.IPlayback;
import com.vpaliy.bakingapp.media.MediaPlayback;
import com.vpaliy.bakingapp.mvp.contract.RecipeSummaryContract;
import com.vpaliy.bakingapp.mvp.contract.RecipesContract;
import com.vpaliy.bakingapp.mvp.presenter.SummaryPresenter;
import com.vpaliy.bakingapp.mvp.presenter.RecipesPresenter;

import com.vpaliy.bakingapp.di.scope.ViewScope;
import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {


    @ViewScope
    @Provides
    RecipesContract.Presenter recipesPresenter(RecipesPresenter presenter){
        return presenter;
    }

    @ViewScope
    @Provides
    RecipeSummaryContract.Presenter recipeDetailsPresenter(SummaryPresenter presenter){
        return presenter;
    }

    @ViewScope
    @Provides
    IPlayback<?> providePlayback(Context context){
        return new MediaPlayback(context);
    }

}
