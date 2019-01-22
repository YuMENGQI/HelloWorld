import android.support.v7.widget.RecyclerView;

/**
 * @author yu
 * @version 1.0
 * @date 2019/1/9 11:40
 * @description
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration{
    private  int leftRight;
    private  int topBottom;
    public  SpacesItemDecoration(int leftRight,int topBottom){
        this.leftRight=leftRight;
        this.topBottom=topBottom;
    }

}
