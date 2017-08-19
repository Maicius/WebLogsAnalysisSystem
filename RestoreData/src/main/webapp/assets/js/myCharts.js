//画总次数、总流量、最多访问路径图
function drawNumChart(domName, data, PieName) {
    let option1 = {
        title: {
            text: PieName,
            left: 'center',
            textStyle: {
                color: '#fff'
            }
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        backgroundColor: backColor,
        series: [{
            type: 'pie',
            radius: ['46%', '75%'],
            silent: true,
            data: [{
                value: 1,
                itemStyle: {
                    normal: {
                        color: '#A4D3EE',
                        borderColor: '#97FFFF',
                        borderWidth: 2,
                        shadowBlur: 50,
                        shadowColor: 'rgba(21,41,185,.75)'
                    }
                }
            }]
        }, {
            name: '占比',
            type: 'pie',
            radius: ['50%', '70%'],
            hoverAnimation: false,
            data: [{
                value: 100,
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: '#FFA54F'
                    }, {
                        offset: 1,
                        color: '#FF4040'
                    }])
                },
                label: {
                    normal: {
                        position: 'outside',
                        textStyle: {
                            color: '#fff',
                            fontSize: 14
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: true,
                        length: 20,
                        length2: 30,
                        smooth: false,
                        lineStyle: {
                            width: 1,
                            color: "#FFEC8B"
                        }
                    }
                }
            }]
        }, {
            name: '',
            type: 'pie',
            clockWise: true,
            hoverAnimation: true,
            radius: [200, 200],
            label: {
                normal: {
                    position: 'center'
                }
            },
            data: [{
                value: 0,
                label: {
                    normal: {
                        formatter: data,
                        textStyle: {
                            color: '#EEC900',
                            fontSize: 28,
                            fontWeight: 'bold'
                        }
                    }
                }
            }]
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    domName.setOption(option1);
}

// 画流量与访问量的关系图
function drawSeqChart(dom, ByteData, ReqData) {
    let option = {
        title: {
            text: "流量和访问量变化曲线图",
            x: 'center',
            left: 'center',
            textStyle: {
                color: '#fff'
            }
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                animation: false
            }
        },
        backgroundColor: backColor,
        legend: {
            data: [{name: '流量', textStyle: {color: '#999'}},
                {name: '访问量', textStyle: {color: '#999'}}],
            x: 'left'
        },
        toolbox: {
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                restore: {},
                saveAsImage: {}
            }
        },
        axisPointer: {
            link: {xAxisIndex: 'all'}
        },
        dataZoom: [
            {
                show: true,
                realtime: true,
                start: 30,
                end: 70,
                xAxisIndex: [0, 1],
                textStyle: {
                    color: "#999"
                },
            },
            {
                type: 'inside',
                realtime: true,
                start: 30,
                end: 70,
                xAxisIndex: [0, 1]
            }
        ],
        grid: [{
            left: 50,
            right: 50,
            height: '35%'
        }, {
            left: 50,
            right: 50,
            top: '55%',
            height: '35%'
        }],
        xAxis: [
            {
                type: 'category',
                boundaryGap: false,
                axisLine: {onZero: true},
                data: ReqData.map(function (item) {
                    return item[0];
                }),
                axisLabel: {
                    inside: false,
                    textStyle: {
                        color: '#999'
                    }
                }
            },
            {
                gridIndex: 1,
                type: 'category',
                boundaryGap: false,
                axisLine: {onZero: true},
                data: ByteData.map(function (item) {
                    return item[0];
                }),
                position: 'top',
                axisLabel: {
                    inside: false,
                    textStyle: {
                        color: '#999'
                    }
                }
            }
        ],
        yAxis: [
            {
                name: '请求数(/次)',
                nameTextStyle: {
                    color: '#999'
                },
                type: 'value',
                axisLabel: {
                    inside: false,
                    textStyle: {
                        color: '#999'
                    }
                }
            },
            {
                gridIndex: 1,
                name: '流量(MB)',
                nameTextStyle: {
                    color: '#999'
                },
                type: 'value',
                inverse: true,
                axisLabel: {
                    inside: false,
                    textStyle: {
                        color: '#999'
                    }
                }
            }
        ],
        series: [
            {
                name: '访问量',
                type: 'line',
                symbolSize: 8,
                hoverAnimation: false,
                data: ReqData.map(function (item) {
                    return item[1];
                }),
                itemStyle: {
                    normal: {
                        color: '#00BFFF'
                    }
                }
            },
            {
                name: '流量',
                type: 'line',
                xAxisIndex: 1,
                yAxisIndex: 1,
                symbolSize: 8,
                hoverAnimation: false,
                data: ByteData.map(function (item) {
                    return (item[1] / 1024 /8).toFixed(2);
                }),
                itemStyle: {
                    normal: {
                        color: '#188df0'
                    }
                }
            }
        ]
    }
    dom.setOption(option);
}

//画历史流量
function drawBarChart(domName, data) {
    let dataAxis = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];

    let yMax = 500;
    let dataShadow = [];

    for (let i = 0; i < data.length; i++) {
        dataShadow.push(yMax);
    }
    let option = {
        title: {
            text: '每小时流量数与访问次数统计表',
            left: 'center',
            textStyle: {
                color: '#fff'
            }
        },
        toolbox: {
            feature: {
                restore: {},
                saveAsImage: {}
            }
        },
        backgroundColor: backColor,
        xAxis: {
            data: dataAxis,
            axisLabel: {
                inside: false,
                textStyle: {
                    color: '#999'
                }
            },
            axisTick: {
                show: false
            },
            axisLine: {
                show: false
            },
            z: 10
        },
        yAxis: {
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLabel: {
                textStyle: {
                    color: '#999'
                }
            }
        },
        dataZoom: [
            {
                type: 'inside'
            }
        ],
        series: [
            { // For shadow
                type: 'bar',
                itemStyle: {
                    normal: {color: 'rgba(0,0,0,0.05)'}
                },
                barGap: '-100%',
                barCategoryGap: '40%',
                data: dataShadow,
                animation: false
            },
            {
                type: 'bar',
                label: {
                    normal: {
                        show: true,
                        position: 'inside'
                    }
                },
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#CD4F39'},
                                {offset: 0.5, color: '#CD950C'},
                                {offset: 1, color: '#CD2990'}
                            ]
                        )
                    },
                    emphasis: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#CD8162'},
                                {offset: 0.7, color: '#CD2990'},
                                {offset: 1, color: '#CD4F39'}
                            ]
                        )
                    }
                },
                data: data
            }
        ]
    };
    domName.setOption(option);
}

//排行数据
function drawRankChart(domName, data, chartName, color) {
    let option = {
        title: {
            text: chartName,
            left: 'center',
            textStyle: {
                color: '#fff'
            }
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        backgroundColor: backColor,
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01],
            axisLabel: {
                textStyle: {
                    color: '#999'
                }
            }
        },
        yAxis: {
            type: 'category',
            data: data.map(function (item) {
                return item[0];
            }),
            axisLabel: {
                textStyle: {
                    color: '#999'
                }
            },

        },
        series: [
            {
                name: chartName,
                type: 'bar',
                data: data.map(function (item) {
                    return item[1];
                }),
                label: {
                    normal: {
                        show: true,
                        position: 'inside'
                    }
                },
                itemStyle: {
                    normal: {
                        // callback,设定每一bar颜色,配置项color顶axis一组bars颜色
                        color: function (params) {
                            var num = color.length;
                            return color[params.dataIndex % num]
                        }
                    }
                }
            }
        ]
    };
    domName.setOption(option);
}

function drawCourtRank(domName, courtData) {
    let courtChart = echarts.init(document.getElementById(domName));
    let option = {
        title: {
            text: '各家法院数据统计图',
            left: 'center',
            textStyle: {
                color: '#fff',
                fontStyle: 'normal',
                fontFamily: '微软雅黑',
                fontSize: 16,
            }
        },
        backgroundColor: backColor,
        tooltip: { //提示框组件
            trigger: 'axis',
            formatter: '{b}<br />{a0}: {c0}<br />{a1}: {c1}',
            axisPointer: {
                type: 'shadow',
                label: {
                    backgroundColor: '#6a7985'
                }
            },

        },
        grid: {
            left: '1%',
            right: '4%',
            bottom: '6%',
            top: 30,
            padding: '0 0 10 0',
            containLabel: true,
        },
        legend: {//图例组件，颜色和名字
            right: 10,
            top: 0,
            itemGap: 16,
            itemWidth: 18,
            itemHeight: 10,
            data: [{
                name: '访问量',
                //icon:'image://../wwwroot/js/url2.png', //路径
            },
                {
                    name: 'IP数',
                }, {
                    name: '流量',
                }
            ],
            textStyle: {
                color: '#a8aab0',
                fontStyle: 'normal',
                fontFamily: '微软雅黑',
                fontSize: 12,
            }
        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: true,//坐标轴两边留白
                data: ['鲁班七号', '王昭君', '阿珂', '赵云', '张飞', '孙膑', '安琪拉', '李白', '花木兰', '妲己', '刘备', '诸葛亮', '关羽'],
                axisLabel: { //坐标轴刻度标签的相关设置。
                    interval: 0,//设置为 1，表示『隔一个标签显示一个标签』
                    margin: 15,
                    textStyle: {
                        color: '#078ceb',
                        fontStyle: 'normal',
                        fontFamily: '微软雅黑',
                        fontSize: 12,

                    }
                },
                axisTick: {//坐标轴刻度相关设置。
                    show: false,
                },
                axisLine: {//坐标轴轴线相关设置
                    lineStyle: {
                        color: '#fff',
                        opacity: 0.2
                    }
                },
                splitLine: { //坐标轴在 grid 区域中的分隔线。
                    show: false,
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                splitNumber: 5,
                axisLabel: {
                    textStyle: {
                        color: '#a8aab0',
                        fontStyle: 'normal',
                        fontFamily: '微软雅黑',
                        fontSize: 12,
                    }
                },
                axisLine: {
                    show: true
                },
                axisTick: {
                    show: false
                },
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: ['#fff'],
                        opacity: 0.06
                    }
                }

            }
        ],
        dataZoom: [
            {
                show: true,
                height: 30,
                xAxisIndex: [0],
                bottom: 0,
                type: 'slider',
                start: 1,
                end: 35,
                handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                handleSize: '110%',
                handleStyle: {
                    color: '#9B4E4E'
                }
            },
            {
                show: true,
                type: 'inside',
                height: 15,
                start: 1,
                end: 35
            }
        ],
        series: [
            {
                name: '访问量',
                type: 'bar',
                data: [4.9, 7.3, 9.2, 5.6, 7.7, 5.6, 4.2, 3.6, 6, 6.4, 5.2, 4.8, 5.5],
                barWidth: 15,
                barGap: 0,//柱间距离
                label: {//图形上的文本标签
                    normal: {
                        show: true,
                        position: 'top',
                        textStyle: {
                            color: '#a8aab0',
                            fontStyle: 'normal',
                            fontFamily: '微软雅黑',
                            fontSize: 12,
                        },
                    },
                },
                itemStyle: {//图形样式
                    normal: {
                        barBorderRadius: [5, 5, 0, 0],
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 1, color: 'rgba(127, 128, 225, 0.7)'
                        }, {
                            offset: 0.9, color: 'rgba(72, 73, 181, 0.7)'
                        }, {
                            offset: 0.31, color: 'rgba(0, 208, 208, 0.7)'
                        }, {
                            offset: 0.15, color: 'rgba(0, 208, 208, 0.7)'
                        }, {
                            offset: 0, color: 'rgba(104, 253, 255, 0.7)'
                        }], false),
                    },
                },
            },
            {
                name: 'IP数',
                type: 'bar',
                data: [2.9, 5, 4.4, 2.7, 5.7, 4.6, 1.2, 2.7, 4.8, 6.0, 3.5, 2.5, 4.5],
                barWidth: 15,
                barGap: 0.2,//柱间距离
                label: {//图形上的文本标签
                    normal: {
                        show: true,
                        position: 'top',
                        textStyle: {
                            color: '#a8aab0',
                            fontStyle: 'normal',
                            fontFamily: '微软雅黑',
                            fontSize: 12,
                        },
                    },
                },
                itemStyle: {//图形样式
                    normal: {
                        barBorderRadius: [5, 5, 0, 0],
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 1, color: 'rgba(127, 128, 225, 0.7)'
                        }, {
                            offset: 0.9, color: 'rgba(72, 73, 181, 0.7)'
                        }, {
                            offset: 0.25, color: 'rgba(226, 99, 74, 0.7)'
                        }, {
                            offset: 0, color: 'rgba(253, 200, 106, 0.7)'
                        }], false),
                    },
                },
            },
            {
                name: '流量',
                type: 'line',
                symbolSize: 10,
                symbol: 'circle',
                itemStyle: {
                    normal: {
                        color: '#CB6C1F',
                        barBorderRadius: 0,
                        label: {
                            show: true,
                            position: 'top',
                            formatter: function (p) {
                                return p.value > 0 ? (p.value) : '';
                            }
                        }
                    }
                },
                data: ['7.0', '13.3', '13.6', '8.3', '13.4', '10.2', '5.4', '6.3', '10.8', '12.4', '8.7', '7.3', '10.0']
            }

        ]
    };
    courtChart.setOption(option);

}

function drawPieChart(domName, methodName, methodData, stateName, stateData) {
    let option = {
        title: [{
            text: '请求方法对比图',
            textStyle: {
                color: '#fff',
                fontStyle: 'normal',
                fontFamily: '微软雅黑',
                fontSize: 16,
            },
            x: 'left'
        }, {
            text: '请求码对比图',
            x: 'right',
            textStyle: {
                color: '#fff',
                fontStyle: 'normal',
                fontFamily: '微软雅黑',
                fontSize: 16,
            }
        }],
        backgroundColor: backColor,
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: [{
            x: 'left',
            y: 'bottom',
            data: methodName
        }, {
            x: 'right',
            y: 'bottom',
            data: stateName
            }
        ],
        color: ['#915872', '#CD950C', '#9a8169', '#3f8797', '#078ceb'],
        calculable: true,
        series: [
            {
                name: '请求方法',
                type: 'pie',
                radius: [20, 110],
                center: ['25%', '50%'],
                roseType: 'radius',
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                lableLine: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                data: methodData
            },
            {
                name: '状态码占比',
                type: 'pie',
                radius: [30, 110],
                center: ['75%', '50%'],
                roseType: 'radius',
                data: stateData
            }
        ]
    };
    domName.setOption(option);
}

function convertJsonToArray(strData) {
    strData = JSON.parse(strData);
    let arr=[];
    for(let p in strData){
        arr.push([p, strData[p]]);
    }
    return arr;
}