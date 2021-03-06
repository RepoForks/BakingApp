package com.vpaliy.bakingapp.mvp.presenter;

import com.vpaliy.bakingapp.domain.model.Step;
import com.vpaliy.bakingapp.mvp.MessageProvider;
import com.vpaliy.bakingapp.mvp.contract.RecipeStepsContract;
import com.vpaliy.bakingapp.mvp.contract.RecipeStepsContract.View;
import java.util.List;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import static com.google.android.exoplayer2.util.Assertions.checkNotNull;

public class StepsPresenter implements RecipeStepsContract.Presenter{

    private View view;
    private MessageProvider messageProvider;
    private Step currentStep;
    private StepsWrapper wrapper;

    public StepsPresenter(@NonNull StepsWrapper wrapper,
                          @NonNull MessageProvider messageProvider){
        this.wrapper=wrapper;
        this.messageProvider=messageProvider;
    }

    @Override
    public void attachView(@NonNull View view) {
        checkNotNull(view);
        this.view=view;
    }

    @Override
    public void stop() {
        view.pauseVideo();
    }

    @Override
    public void showCurrent() {
        Step step=currentStep;
        if(wrapper.isInBounds()){
            step=wrapper.current();
        }
        currentStep=step;
        if(currentStep!=null){
            view.showPageNumber(wrapper.currentIndex,wrapper.count());
            String shortDescription=currentStep.getShortDescription();
            String description=currentStep.getDescription();
            if(TextUtils.equals(shortDescription,description)){
                description=null;
            }
            view.showDescription(shortDescription,description);
            manageButtons();
            if(playVideo(currentStep.getVideoUrl())||playVideo(currentStep.getImageUrl())){
                return;
            }
            view.hidePlayer();
        }else{
            view.showMessage(messageProvider.emptyMessage());
        }
    }

    private boolean playVideo(String url){
        if(!TextUtils.isEmpty(url)){
            view.playVideo(url);
            return true;
        }
        return false;
    }

    private void manageButtons(){
        if(wrapper.currentIndex==0){
            view.hidePrevButton();
        }else{
            view.showPrevButton();
        }

        if(wrapper.currentIndex>=wrapper.count()-1){
            view.hideNextButton();
        }else{
            view.showNextButton();
        }
    }

    @Override
    public void requestStep(int step) {
        if(step>=0 && step<wrapper.count()){
            wrapper.currentIndex=step;
            showCurrent();
        }
    }

    @Override
    public void showNext() {
        if((wrapper.currentIndex+1)<wrapper.count()) {
            wrapper.currentIndex++;
            showCurrent();
        }
    }

    @Override
    public void showPrev() {
        if((wrapper.currentIndex-1)>=0) {
            wrapper.currentIndex--;
            showCurrent();
        }
    }

    public static class StepsWrapper {
        private int currentIndex;
        private List<Step> steps;

        public int count(){
            return steps.size();
        }

        public Step current(){
            return steps.get(currentIndex);
        }

        public boolean isInBounds(){
            return currentIndex <=count();
        }

        private StepsWrapper(List<Step> steps,int currentStep){
            this.steps=steps;
            this.currentIndex =currentStep;
        }

        public static StepsWrapper wrap(List<Step> steps, int currentStep){
            return new StepsWrapper(steps,currentStep);
        }
    }
}
