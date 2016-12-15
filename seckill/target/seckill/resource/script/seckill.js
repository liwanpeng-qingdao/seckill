/**
 * Created by OUC on 2016/12/13.
 */
var seckill = {
    URL: {
        now: function () {
            return "/seckill/time/now";
        },
        exposer: function (seckillId) {
            return "/seckill/" + seckillId + "/exposer";
        },
        excute: function (seckillId, md5) {
            return "/seckill/" + seckillId + "/" + md5 + "/excute";
        }
    },

    //验证手机号
    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            //验证成功
            return true;
        }
        else {
            return false;
        }
    },
    //暴露秒杀接口，并绑定到秒杀按钮
    seckillKillExcute: function (seckillId, countDownBox) {
        countDownBox.hide()
            .html("<button class='btn btn-primary btn-lg' id='excuteKillBtn'>开始秒杀</button>");
        //获取秒杀的exposer
        $.post(seckill.URL.exposer(seckillId), {}, function (result) {
            if (result['success'] && result) {
                var exposer = result['data'];
                //防止计时器不准确，再做一次验证，，其实不做也可以
                if (exposer['exposed']) {
                    //秒杀已开启
                    var md5 = exposer['md5'];
                    //绑定一次点击事件
                    console.log("excuteurl=" + seckill.URL.excute(seckillId, md5));
                    var sdf = $("#excuteKillBtn");
                    $('#excuteKillBtn').one('click', function () {
                        //禁用，防止多次发送请求
                        $(this).addClass('disabled');
                        $.post(seckill.URL.excute(seckillId, md5), {}, function (result) {
                            if (result && result['success']) {
                                var killResult = result['data'];
                                var state = killResult['state'];
                                var stateInfo = killResult['stateInfo'];
                                //秒杀结果显示
                                countDownBox.html('<span class="label label-success">' + stateInfo + '</span>')
                            }
                        })
                    })
                    countDownBox.show();
                } else {
                    //未开启秒杀，重新计时
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    seckill.timeCountDown(seckillId, now, start, end)
                }
            }
        })

    },
    //倒计时方法
    timeCountDown: function (seckillId, nowTime, startTime, endTime) {
        var countDownBox = $('#seckill-box');

        if (nowTime > endTime) {
            //秒杀结束
            countDownBox.html("秒杀结束！");
        } else if (nowTime < startTime) {
            //秒杀未开始，进行倒计时
            var killStartTime = new Date(startTime + 1000);
            //绑定倒计时容器
            countDownBox.countdown(killStartTime, function (event) {
                //日期格式化
                var timeFormat = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
                countDownBox.html(timeFormat);
            }).on('finish.countdown', function () {
                //获取秒杀按钮，并显示到页面上
                seckill.seckillKillExcute(seckillId, countDownBox);
            })
        } else {
            //秒杀开始，暴露秒杀接口
            seckill.seckillKillExcute(seckillId, countDownBox);
        }

    },
    deatail: {
        //初始化详情页
        init: function (params) {
            var killPhone = $.cookie('killPhone')
            var seckillId = params['seckillId']
            //初始化时验证手机号，功能和验证是否登录相似
            if (!seckill.validatePhone(killPhone)) {
                //手机号输入的弹出层
                var killPhoneModal = $('#killPhoneMod');
                //显示弹出层，并对其内部方法进行初始化
                killPhoneModal.modal({
                    show: true,//显示
                    backdrop: 'static',//禁止位置关闭（点击非本弹出框的位置弹出框就会关闭的功能）
                    keyboard: false//禁止键盘事件（键盘按吓esc键，本弹出框关闭的功能）
                });
                $("#killPhoneBtn").click(function () {
                    var inputPhone = $("#killPhoneKey").val();
                    if (seckill.validatePhone(inputPhone)) {
                        //如果手机格式正确，写入cookie
                        $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'});
                        //页面刷新，执行其他操作
                        window.location.reload();
                    } else {
                        //像手机号错误这种提示信息，也是需要做一个前端字典来实现，这里没做，注意一下
                        $('#killPhoneMessage').hide().html('<labale class="label label-danger">手机号错误！</labale>').show(300);

                    }


                })
            }
            //已经登录
            //获取服务器时间,进行倒计时操作
            var startTime = params['startTime']
            var endTime = params['endTime']
            $.get(seckill.URL.now(), {}, function (result) {
                if (result && result['success']) {
                    //获取系统时间，进行倒计时
                    var timeNow = result['data'];
                    seckill.timeCountDown(seckillId, timeNow, startTime, endTime);
                } else {
                    console.log('result=' + result);
                }
            })
        }
    }

}