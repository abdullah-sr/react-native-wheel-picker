import React from 'react';
import PropTypes from 'prop-types';
import {
    View,
    ColorPropType,
    requireNativeComponent,
} from 'react-native';


class WheelCurvedPicker extends React.Component {
    propTypes: {
        ...View.propTypes,
        data: PropTypes.array,
        selectedIndex: PropTypes.number,
        selectTextColor: ColorPropType,
        textSize: PropTypes.number,
        textColor: ColorPropType,
        indicatorSize: PropTypes.number,
        indicatorColor: ColorPropType,
        indicator: PropTypes.bool,
        curtain: PropTypes.bool,
        curtainColor: ColorPropType,
        atmospheric: PropTypes.bool,
        curved: PropTypes.bool,
        visibleItemCount: PropTypes.number,
        itemSpace: PropTypes.number,
        onValueChange: PropTypes.func,
        selectedValue: PropTypes.any,
    }

    constructor(props) {
        super(props);
        this.state = this._stateFromProps(props);
        this._onValueChange = this._onValueChange.bind(this);
    }

    componentWillReceiveProps(nextProps) {
        this.setState(this._stateFromProps(nextProps));
    }

    _stateFromProps(props) {
        let selectedIndex = 0;
        const items = [];
        React.Children.forEach(props.children, (child, index) => {
            if (child.props.value === props.selectedValue) {
                selectedIndex = index;
            }
            items.push({ value: index, theValue: child.props.value, label: child.props.label });
        });

        const textSize = props.fontSize;
        const textColor = props.textColor;
        const selectTextColor = props.selectTextColor;
        const itemSpace = props.itemSpace;
        const indicator = props.indicator;
        const indicatorColor = props.color;
        const indicatorSize = props.fontSize;
        const curtain = props.curtain;
        const curtainColor = props.curtainColor;
        const atmospheric = props.atmospheric;
        const curved = props.curved;
        const visibleItemCount = props.visibleItemCount;

        return {
            selectedIndex,
            items,
            textSize,
            textColor,
            selectTextColor,
            itemSpace,
            indicator,
            indicatorColor,
            indicatorSize,
            curtain,
            curtainColor,
            atmospheric,
            curved,
            visibleItemCount
        };
    }

    _onValueChange(e) {
        if (this.props.onValueChange) {
            let selectedItem = this.state.items[e.nativeEvent.data];
            !selectedItem && (selectedItem = { theValue: 0 });
            this.props.onValueChange(selectedItem.theValue);
        }
    }

    render() {
        return (
            <WheelCurvedPickerNative
                {...this.props}
                onValueChange={this._onValueChange}
                data={this.state.items}
                selectedIndex={parseInt(this.state.selectedIndex)}
                textColor={this.state.textColor}
                textSize={this.state.textSize}
                selectTextColor={this.state.selectTextColor}
                itemSpace={this.state.itemSpace}
                indicator={this.state.indicator}
                indicatorColor={this.state.indicatorColor}
                indicatorSize={this.state.indicatorSize}
                curtain={this.state.curtain}
                atmospheric={this.state.atmospheric}
                curved={this.state.curved}
                visibleItemCount={this.state.visibleItemCount}
            />
        );
    }
}


const WheelCurvedPickerNative = requireNativeComponent('WheelCurvedPicker', WheelCurvedPicker);

class Item extends React.Component {
    propTypes: {
        value: PropTypes.any, // string or integer basically
        label: PropTypes.string,
    }

    render() {
        // These items don't get rendered directly.
        return null;
    }
}

WheelCurvedPicker.Item = Item;

module.exports = WheelCurvedPicker;
