package com.zyu;

import android.graphics.Color;

import com.aigestudio.wheelpicker.WheelPicker;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author <a href="mailto:lesliesam@hotmail.com"> Sam Yu </a>
 */
public class ReactWheelCurvedPickerManager extends SimpleViewManager<ReactWheelCurvedPicker> {

    private static final String REACT_CLASS = "WheelCurvedPicker";

    private static final int DEFAULT_TEXT_SIZE = 50;
    private static final int DEFAULT_ITEM_SPACE = 25;

    @Override
    protected ReactWheelCurvedPicker createViewInstance(ThemedReactContext reactContext) {
        ReactWheelCurvedPicker picker = new ReactWheelCurvedPicker(reactContext);
        picker.setItemTextColor(Color.LTGRAY);
        picker.setItemTextSize(DEFAULT_TEXT_SIZE);
        picker.setSelectedItemTextColor(Color.WHITE);
        picker.setItemSpace(DEFAULT_ITEM_SPACE);
        picker.setIndicator(true);
        picker.setIndicatorSize(2);
        picker.setIndicatorColor(Color.WHITE);
        picker.setCurtain(false);
        picker.setCurtainColor(Color.YELLOW);
        picker.setAtmospheric(true);
        picker.setCurved(true);
        picker.setVisibleItemCount(5);
        picker.setItemAlign(0);

        return picker;
    }

    @Override
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                ItemSelectedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onValueChange")
        );
    }

    @ReactProp(name="data")
    public void setData(ReactWheelCurvedPicker picker, ReadableArray items) {
        if (picker != null) {
            ArrayList<Integer> valueData = new ArrayList<>();
            ArrayList<String> labelData = new ArrayList<>();
            for (int i = 0; i < items.size(); i ++) {
                ReadableMap itemMap = items.getMap(i);
                valueData.add(itemMap.getInt("value"));
                labelData.add(itemMap.getString("label"));
            }
            picker.setValueData(valueData);
            picker.setData(labelData);
        }
    }

    @ReactProp(name="selectedIndex")
    public void setSelectedIndex(ReactWheelCurvedPicker picker, int index) {
        if (picker != null && picker.getState() == WheelPicker.SCROLL_STATE_IDLE) {
            picker.setSelectedItemPosition(index);
            picker.invalidate();
        }
    }

    @ReactProp(name="textColor", customType = "Color")
    public void setTextColor(ReactWheelCurvedPicker picker, Integer color) {
        if (picker != null) {
            picker.setItemTextColor(color);
        }
    }

    @ReactProp(name="curtainColor", customType = "Color")
    public void setCurtainColor(ReactWheelCurvedPicker picker, Integer color) {
        if (picker != null) {
            picker.setCurtainColor(color);
        }
    }

    @ReactProp(name="textSize")
    public void setTextSize(ReactWheelCurvedPicker picker, int size) {
        if (picker != null) {
            picker.setItemTextSize((int) PixelUtil.toPixelFromDIP(size));
        }
    }

    @ReactProp(name="itemSpace")
    public void setItemSpace(ReactWheelCurvedPicker picker, int space) {
        if (picker != null) {
            picker.setItemSpace((int) PixelUtil.toPixelFromDIP(space));
        }
    }

    @ReactProp(name="indicator")
    public void setIndicator(ReactWheelCurvedPicker picker, boolean hasIndicator) {
        if (picker != null) {
            picker.setIndicator(hasIndicator);
        }
    }

    @ReactProp(name="indicatorColor", customType = "Color")
    public void setIndicatorColor(ReactWheelCurvedPicker picker, Integer color) {
        if (picker != null) {
            picker.setIndicatorColor(color);
        }
    }

    @ReactProp(name="indicatorSize")
    public void setIndicatorSize(ReactWheelCurvedPicker picker, int size) {
        if (picker != null) {
            picker.setIndicatorSize(size);
        }
    }

    @ReactProp(name="curtain")
    public void setCurtain(ReactWheelCurvedPicker picker, boolean hasCurtain) {
        if (picker != null) {
            picker.setCurtain(hasCurtain);
        }
    }

    @ReactProp(name="selectTextColor", customType = "Color")
    public void setSelectedTextColor(ReactWheelCurvedPicker picker, Integer color) {
        if (picker != null) {
            picker.setSelectedItemTextColor(color);
        }
    }

    @ReactProp(name="atmospheric")
    public void setAtmospheric(ReactWheelCurvedPicker picker, boolean hasAtmospheric) {
        if (picker != null) {
            picker.setAtmospheric(hasAtmospheric);
        }
    }

    @ReactProp(name="curved")
    public void setCurved(ReactWheelCurvedPicker picker, boolean hasCurved) {
        if (picker != null) {
            picker.setCurved(hasCurved);
        }
    }

    @ReactProp(name="visibleItemCount")
    public void setVisibleItemCount(ReactWheelCurvedPicker picker, int num) {
        if (picker != null) {
            picker.setVisibleItemCount(num);
        }
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }
}
