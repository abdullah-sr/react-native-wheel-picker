'use strict';

import React from 'react';
import PropTypes from 'prop-types'
import {
        View,
        ColorPropType,
        requireNativeComponent,
        } from 'react-native';


        const WheelCurvedPickerNative = requireNativeComponent('WheelCurvedPicker', WheelCurvedPicker);

class WheelCurvedPicker extends React.Component {

    propTypes: {
        ...View.propTypes,

                data: PropTypes.array,

                selectedIndex: PropTypes.number,

                selectTextColor: ColorPropType,

                itemStyle: PropTypes.object, //textColor  textSize

                textSize: PropTypes.number,

                textColor: ColorPropType,

                indicatorStyle: PropTypes.object, //indicatorColor  indicatorSize

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

    constructor(props){
        super(props)
        this.state = this._stateFromProps(props)
        this._onValueChange = this._onValueChange.bind(this);
    }
    static defaultProps = {
        itemStyle: { color: "white", fontSize: 26 },
        indicatorStyle: { color: "red", fontSize: 2 },
        itemSpace: 20,
    };
    //
    // get state() {
    //     return this._stateFromProps(this.props);
    // }

    componentWillReceiveProps(nextProps) {
        this.setState(this._stateFromProps(nextProps));
    }

    _stateFromProps(props) {
        var selectedIndex = 0;
        var items = [];
        React.Children.forEach(props.children, function (child, index) {
            if (child.props.value === props.selectedValue) {
                selectedIndex = index;
            }
            items.push({ value: index, theValue: child.props.value, label: child.props.label });
        });

        var textSize = props.itemStyle.fontSize
        var textColor = props.itemStyle.color
        var selectTextColor = props.selectTextColor
        var itemSpace = props.itemSpace
        var indicator = props.indicator
        var indicatorColor = props.indicatorStyle.color
        var indicatorSize = props.indicatorStyle.fontSize
        var curtain = props.curtain
        var curtainColor = props.curtainColor
        var atmospheric = props.atmospheric
        var curved = props.curved
        var visibleItemCount = props.visibleItemCount

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
            var selectedItem = this.state.items[e.nativeEvent.data];
            !selectedItem && (selectedItem = { theValue: 0 });
            this.props.onValueChange(selectedItem.theValue);
        }
    }

    render() {
        return <WheelCurvedPickerNative
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
                />;
    }
}

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
