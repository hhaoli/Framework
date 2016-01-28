package com.lihonghao.library.widgets.wheel;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lihonghao.library.R;
import com.lihonghao.library.utils.ScreenUtils;
import com.lihonghao.library.widgets.wheel.adapter.ArrayWheelAdapter;
import com.lihonghao.library.widgets.wheel.listener.OnWheelChangedListener;
import com.lihonghao.library.widgets.wheel.view.WheelView;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：鸿浩
 * 邮箱：hhaoli@sina.cn
 * 时间：2016/1/28
 * 描述：
 */
public class WheelSelect extends PopupWindow implements OnWheelChangedListener {
    private Context context;
    private LayoutInflater inflater;
    Button btnLeft, btnRight;
    TextView title;
    protected View mRootView;

    /**
     * 第一个wheel，以下类推
     */
    private WheelView firstWheelView;

    private WheelView secondWheelView;

    private WheelView thirdWheelView;

    /**
     * 第一层数据，以下类推
     */
    private String[] firstdatas;

    private Map<String, String[]> seconddatasmap = new HashMap<String, String[]>();

    private Map<String, String[]> thirddatasmap = new HashMap<String, String[]>();

    /**
     * 第一层得到的结果，以下类推
     */
    private String mCurrentfirstName = null;

    private String mCurrentSecondName = null;
    private String mCurrentThirdName = null;
    OnSelectListener listener;

    /**
     * 对话框确定与取消按钮回调接
     */
    public interface OnSelectListener {
        /**
         * 确定按钮的回调接口
         *
         * @param result 用城市举例为：省-市-区
         */
        public void doSelectResult(String result);

        /**
         * 取消按钮
         */
        public void doCancel();
    }

    public void setOnSelectListener(OnSelectListener listener) {
        this.listener = listener;
    }

    /**
     * 根据参数来初始化对应wheel的个数
     *
     * @param context
     * @param firstdatas
     * @param seconddatasmap
     * @param thirddatasmap
     * @throws Exception
     */
    public WheelSelect(Context context, String[] firstdatas,
                       Map<String, String[]> seconddatasmap,
                       Map<String, String[]> thirddatasmap) throws Exception {
        super(context);
        this.context = context;
        this.firstdatas = firstdatas;
        this.seconddatasmap = seconddatasmap;
        this.thirddatasmap = thirddatasmap;
        initpopup();
        if (firstdatas == null) {
            throw new Exception("The first parameter can not be null!");
        } else {
            if (thirddatasmap == null && seconddatasmap != null) {
                doubleWheel();
            } else if (seconddatasmap == null && thirddatasmap == null) {
                OneWheel();
            } else if (seconddatasmap != null && thirddatasmap != null) {
                thirdWheel();
            } else {
                throw new Exception(
                        "Invalid argument, set two consecutive parameters!");
            }
        }
    }

    /**
     * 初始化单联动
     */
    private void OneWheel() {
        secondWheelView.setVisibility(View.GONE);
        thirdWheelView.setVisibility(View.GONE);
        firstWheelView.setViewAdapter(new ArrayWheelAdapter<String>(context,
                firstdatas));
        updateOneDatas();
        firstWheelView.addChangingListener(this);
        initClick();
    }

    /**
     * 初始化三联动
     */
    private void thirdWheel() {
        firstWheelView.setViewAdapter(new ArrayWheelAdapter<String>(context,
                firstdatas));
        firstWheelView.addChangingListener(this);
        secondWheelView.addChangingListener(this);
        thirdWheelView.addChangingListener(this);
        firstWheelView.setVisibleItems(3);
        secondWheelView.setVisibleItems(3);
        thirdWheelView.setVisibleItems(3);
        updateSecondDatas();
        updateThirdDatas();
        initClick();

    }

    /**
     * 初始化两联动
     */
    private void doubleWheel() {
        thirdWheelView.setVisibility(View.GONE);
        firstWheelView.setViewAdapter(new ArrayWheelAdapter<String>(context,
                firstdatas));
        firstWheelView.addChangingListener(this);
        secondWheelView.addChangingListener(this);
        firstWheelView.setVisibleItems(3);
        secondWheelView.setVisibleItems(3);
        updateSecondDatas();
        initClick();
    }

    /**
     * popupwindow的初始化操作
     */
    private void initpopup() {
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView = inflater.inflate(R.layout.wheel_select, null);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        firstWheelView = (WheelView) mRootView.findViewById(R.id.wheel_select_province);
        secondWheelView = (WheelView) mRootView.findViewById(R.id.wheel_select_city);
        thirdWheelView = (WheelView) mRootView.findViewById(R.id.wheel_select_area);
        title = (TextView) mRootView.findViewById(R.id.wheel_select_title);
        btnLeft = (Button) mRootView.findViewById(R.id.wheel_select_btn_left);
        btnRight = (Button) mRootView.findViewById(R.id.wheel_select_btn_right);
        mRootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        this.setBackgroundDrawable(new ColorDrawable(0x60000000));
        this.setContentView(mRootView);
        this.setWidth(ScreenUtils.getScreenWidth(context));
        this.setHeight((int) (ScreenUtils.getScreenHeight(context) / 2.5));
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        setAnimationStyle(R.style.ActionSheetDialogAnimation);
        this.update();
    }

    /**
     * 初始化按钮事件
     */
    private void initClick() {
        btnRight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (mCurrentThirdName == null && mCurrentfirstName != null
                        && mCurrentSecondName != null) {
                    listener.doSelectResult(mCurrentfirstName + "-"
                            + mCurrentSecondName);
                } else if (mCurrentThirdName != null
                        && mCurrentfirstName != null
                        && mCurrentSecondName != null) {

                    listener.doSelectResult(mCurrentfirstName + "-"
                            + mCurrentSecondName + "-" + mCurrentThirdName);

                } else if (mCurrentThirdName == null
                        && mCurrentfirstName != null
                        && mCurrentSecondName == null) {
                    listener.doSelectResult(mCurrentfirstName);
                }
                dismiss();
            }
        });
        btnLeft.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dismiss();
                listener.doCancel();
            }
        });
    }

    /**
     * 单个联动数据更新
     */
    private void updateOneDatas() {
        int pCurrent = firstWheelView.getCurrentItem();
        mCurrentfirstName = firstdatas[pCurrent];
        firstWheelView.setVisibleItems(3);
    }

    /**
     * 三联动数据更新
     */
    private void updateThirdDatas() {
        int pCurrent = secondWheelView.getCurrentItem();
        mCurrentSecondName = seconddatasmap.get(mCurrentfirstName)[pCurrent];
        String[] areas = thirddatasmap.get(mCurrentSecondName);

        if (areas == null) {
            areas = new String[]{""};
        }
        thirdWheelView.setViewAdapter(new ArrayWheelAdapter<String>(context,
                areas));
        thirdWheelView.setCurrentItem(0);
        mCurrentThirdName = areas[0];
    }

    /**
     * 两联动数据更新
     */
    private void updateSecondDatas() {
        int pCurrent = firstWheelView.getCurrentItem();
        mCurrentfirstName = firstdatas[pCurrent];
        String[] cities = seconddatasmap.get(mCurrentfirstName);
        if (cities == null) {
            cities = new String[]{""};
        }
        secondWheelView.setViewAdapter(new ArrayWheelAdapter<String>(context,
                cities));
        secondWheelView.setCurrentItem(0);
        if (thirdWheelView.getVisibility() != View.GONE) {
            updateThirdDatas();
        }
        mCurrentSecondName = cities[0];
    }

    /**
     * 设置标题名称
     *
     * @param title
     */
    public void setTitleName(String title) {
        this.title.setText(title);
    }

    /**
     * change事件的处理
     */
    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == firstWheelView
                && secondWheelView.getVisibility() != View.GONE) {
            updateSecondDatas();
        } else if (wheel == secondWheelView
                && thirdWheelView.getVisibility() != View.GONE) {
            updateThirdDatas();
        } else if (wheel == secondWheelView
                && thirdWheelView.getVisibility() == View.GONE) {
            int pCurrent = secondWheelView.getCurrentItem();
            mCurrentSecondName = seconddatasmap.get(mCurrentfirstName)[pCurrent];
        } else if (wheel == thirdWheelView) {
            mCurrentThirdName = thirddatasmap.get(mCurrentSecondName)[newValue];
        } else if (wheel == firstWheelView
                && secondWheelView.getVisibility() == View.GONE) {
            updateOneDatas();
        }
    }
}
