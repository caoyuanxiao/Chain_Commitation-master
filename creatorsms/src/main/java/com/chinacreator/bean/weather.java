package com.chinacreator.bean;

/**
 * Created by Smile on 2017/4/28.
 */

public class weather {

    /**
     * resultcode : 200
     * reason : 查询成功
     * result : {"sk":{"temp":"21","wind_direction":"北风","wind_strength":"4级","humidity":"51%","time":"13:25"},"today":{"temperature":"13℃~22℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"北风微风","week":"星期五","city":"郴州","date_y":"2017年04月28日","dressing_index":"较舒适",
     * "dressing_advice":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。","uv_index":"中等","comfort_index":"","wash_index":"较适宜","travel_index":"较适宜","exercise_index":"较适宜","drying_index":""},"future":{"day_20170428":{"temperature":"13℃~22℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},
     * "wind":"北风微风","week":"星期五","date":"20170428"},"day_20170429":{"temperature":"16℃~27℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"北风微风","week":"星期六","date":"20170429"},"day_20170430":{"temperature":"20℃~27℃","weather":"多云转阵雨","weather_id":{"fa":"01","fb":"03"},"wind":"南风微风",
     * "week":"星期日","date":"20170430"},"day_20170501":{"temperature":"20℃~24℃","weather":"中雨转小雨","weather_id":{"fa":"08","fb":"07"},"wind":"南风微风","week":"星期一","date":"20170501"},"day_20170502":{"temperature":"22℃~29℃","weather":"阵雨转多云","weather_id":{"fa":"03","fb":"01"},"wind":"南风微风",
     * "week":"星期二","date":"20170502"},"day_20170503":{"temperature":"16℃~27℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"北风微风","week":"星期三","date":"20170503"},"day_20170504":{"temperature":"20℃~24℃","weather":"中雨转小雨","weather_id":{"fa":"08","fb":"07"},"wind":"南风微风","week":"星期四",
     * "date":"20170504"}}}
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    private ResultBean result;
    private int error_code;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * sk : {"temp":"21","wind_direction":"北风","wind_strength":"4级","humidity":"51%","time":"13:25"}
         * today : {"temperature":"13℃~22℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"北风微风","week":"星期五","city":"郴州","date_y":"2017年04月28日","dressing_index":"较舒适","dressing_advice":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。","uv_index":"中等","comfort_index":"","wash_index":"较适宜",
         * "travel_index":"较适宜","exercise_index":"较适宜","drying_index":""}
         * future : {"day_20170428":{"temperature":"13℃~22℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"北风微风","week":"星期五","date":"20170428"},"day_20170429":{"temperature":"16℃~27℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"北风微风","week":"星期六","date":"20170429"},
         * "day_20170430":{"temperature":"20℃~27℃","weather":"多云转阵雨","weather_id":{"fa":"01","fb":"03"},"wind":"南风微风","week":"星期日","date":"20170430"},"day_20170501":{"temperature":"20℃~24℃","weather":"中雨转小雨","weather_id":{"fa":"08","fb":"07"},"wind":"南风微风","week":"星期一","date":"20170501"},
         * "day_20170502":{"temperature":"22℃~29℃","weather":"阵雨转多云","weather_id":{"fa":"03","fb":"01"},"wind":"南风微风","week":"星期二","date":"20170502"},"day_20170503":{"temperature":"16℃~27℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"北风微风","week":"星期三","date":"20170503"},
         * "day_20170504":{"temperature":"20℃~24℃","weather":"中雨转小雨","weather_id":{"fa":"08","fb":"07"},"wind":"南风微风","week":"星期四","date":"20170504"}}
         */

        private SkBean sk;
        private TodayBean today;
        private FutureBean future;

        public SkBean getSk() {
            return sk;
        }

        public void setSk(SkBean sk) {
            this.sk = sk;
        }

        public TodayBean getToday() {
            return today;
        }

        public void setToday(TodayBean today) {
            this.today = today;
        }

        public FutureBean getFuture() {
            return future;
        }

        public void setFuture(FutureBean future) {
            this.future = future;
        }

        public static class SkBean {
            /**
             * temp : 21
             * wind_direction : 北风
             * wind_strength : 4级
             * humidity : 51%
             * time : 13:25
             */

            private String temp;
            private String wind_direction;
            private String wind_strength;
            private String humidity;
            private String time;

            public String getTemp() {
                return temp;
            }

            public void setTemp(String temp) {
                this.temp = temp;
            }

            public String getWind_direction() {
                return wind_direction;
            }

            public void setWind_direction(String wind_direction) {
                this.wind_direction = wind_direction;
            }

            public String getWind_strength() {
                return wind_strength;
            }

            public void setWind_strength(String wind_strength) {
                this.wind_strength = wind_strength;
            }

            public String getHumidity() {
                return humidity;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }

        public static class TodayBean {
            /**
             * temperature : 13℃~22℃
             * weather : 晴
             * weather_id : {"fa":"00","fb":"00"}
             * wind : 北风微风
             * week : 星期五
             * city : 郴州
             * date_y : 2017年04月28日
             * dressing_index : 较舒适
             * dressing_advice : 建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。
             * uv_index : 中等
             * comfort_index :
             * wash_index : 较适宜
             * travel_index : 较适宜
             * exercise_index : 较适宜
             * drying_index :
             */

            private String temperature;
            private String weather;
            private WeatherIdBean weather_id;
            private String wind;
            private String week;
            private String city;
            private String date_y;
            private String dressing_index;
            private String dressing_advice;
            private String uv_index;
            private String comfort_index;
            private String wash_index;
            private String travel_index;
            private String exercise_index;
            private String drying_index;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WeatherIdBean getWeather_id() {
                return weather_id;
            }

            public void setWeather_id(WeatherIdBean weather_id) {
                this.weather_id = weather_id;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDate_y() {
                return date_y;
            }

            public void setDate_y(String date_y) {
                this.date_y = date_y;
            }

            public String getDressing_index() {
                return dressing_index;
            }

            public void setDressing_index(String dressing_index) {
                this.dressing_index = dressing_index;
            }

            public String getDressing_advice() {
                return dressing_advice;
            }

            public void setDressing_advice(String dressing_advice) {
                this.dressing_advice = dressing_advice;
            }

            public String getUv_index() {
                return uv_index;
            }

            public void setUv_index(String uv_index) {
                this.uv_index = uv_index;
            }

            public String getComfort_index() {
                return comfort_index;
            }

            public void setComfort_index(String comfort_index) {
                this.comfort_index = comfort_index;
            }

            public String getWash_index() {
                return wash_index;
            }

            public void setWash_index(String wash_index) {
                this.wash_index = wash_index;
            }

            public String getTravel_index() {
                return travel_index;
            }

            public void setTravel_index(String travel_index) {
                this.travel_index = travel_index;
            }

            public String getExercise_index() {
                return exercise_index;
            }

            public void setExercise_index(String exercise_index) {
                this.exercise_index = exercise_index;
            }

            public String getDrying_index() {
                return drying_index;
            }

            public void setDrying_index(String drying_index) {
                this.drying_index = drying_index;
            }

            public static class WeatherIdBean {
                /**
                 * fa : 00
                 * fb : 00
                 */

                private String fa;
                private String fb;

                public String getFa() {
                    return fa;
                }

                public void setFa(String fa) {
                    this.fa = fa;
                }

                public String getFb() {
                    return fb;
                }

                public void setFb(String fb) {
                    this.fb = fb;
                }
            }
        }

        public static class FutureBean {
            /**
             * day_20170428 : {"temperature":"13℃~22℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"北风微风","week":"星期五","date":"20170428"}
             * day_20170429 : {"temperature":"16℃~27℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"北风微风","week":"星期六","date":"20170429"}
             * day_20170430 : {"temperature":"20℃~27℃","weather":"多云转阵雨","weather_id":{"fa":"01","fb":"03"},"wind":"南风微风","week":"星期日","date":"20170430"}
             * day_20170501 : {"temperature":"20℃~24℃","weather":"中雨转小雨","weather_id":{"fa":"08","fb":"07"},"wind":"南风微风","week":"星期一","date":"20170501"}
             * day_20170502 : {"temperature":"22℃~29℃","weather":"阵雨转多云","weather_id":{"fa":"03","fb":"01"},"wind":"南风微风","week":"星期二","date":"20170502"}
             * day_20170503 : {"temperature":"16℃~27℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"北风微风","week":"星期三","date":"20170503"}
             * day_20170504 : {"temperature":"20℃~24℃","weather":"中雨转小雨","weather_id":{"fa":"08","fb":"07"},"wind":"南风微风","week":"星期四","date":"20170504"}
             */

            private Day20170428Bean day_20170428;
            private Day20170429Bean day_20170429;
            private Day20170430Bean day_20170430;
            private Day20170501Bean day_20170501;
            private Day20170502Bean day_20170502;
            private Day20170503Bean day_20170503;
            private Day20170504Bean day_20170504;

            public Day20170428Bean getDay_20170428() {
                return day_20170428;
            }

            public void setDay_20170428(Day20170428Bean day_20170428) {
                this.day_20170428 = day_20170428;
            }

            public Day20170429Bean getDay_20170429() {
                return day_20170429;
            }

            public void setDay_20170429(Day20170429Bean day_20170429) {
                this.day_20170429 = day_20170429;
            }

            public Day20170430Bean getDay_20170430() {
                return day_20170430;
            }

            public void setDay_20170430(Day20170430Bean day_20170430) {
                this.day_20170430 = day_20170430;
            }

            public Day20170501Bean getDay_20170501() {
                return day_20170501;
            }

            public void setDay_20170501(Day20170501Bean day_20170501) {
                this.day_20170501 = day_20170501;
            }

            public Day20170502Bean getDay_20170502() {
                return day_20170502;
            }

            public void setDay_20170502(Day20170502Bean day_20170502) {
                this.day_20170502 = day_20170502;
            }

            public Day20170503Bean getDay_20170503() {
                return day_20170503;
            }

            public void setDay_20170503(Day20170503Bean day_20170503) {
                this.day_20170503 = day_20170503;
            }

            public Day20170504Bean getDay_20170504() {
                return day_20170504;
            }

            public void setDay_20170504(Day20170504Bean day_20170504) {
                this.day_20170504 = day_20170504;
            }

            public static class Day20170428Bean {
                /**
                 * temperature : 13℃~22℃
                 * weather : 晴
                 * weather_id : {"fa":"00","fb":"00"}
                 * wind : 北风微风
                 * week : 星期五
                 * date : 20170428
                 */

                private String temperature;
                private String weather;
                private WeatherIdBeanX weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBeanX getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBeanX weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBeanX {
                    /**
                     * fa : 00
                     * fb : 00
                     */

                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }

            public static class Day20170429Bean {
                /**
                 * temperature : 16℃~27℃
                 * weather : 晴
                 * weather_id : {"fa":"00","fb":"00"}
                 * wind : 北风微风
                 * week : 星期六
                 * date : 20170429
                 */

                private String temperature;
                private String weather;
                private WeatherIdBeanXX weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBeanXX getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBeanXX weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBeanXX {
                    /**
                     * fa : 00
                     * fb : 00
                     */

                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }

            public static class Day20170430Bean {
                /**
                 * temperature : 20℃~27℃
                 * weather : 多云转阵雨
                 * weather_id : {"fa":"01","fb":"03"}
                 * wind : 南风微风
                 * week : 星期日
                 * date : 20170430
                 */

                private String temperature;
                private String weather;
                private WeatherIdBeanXXX weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBeanXXX getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBeanXXX weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBeanXXX {
                    /**
                     * fa : 01
                     * fb : 03
                     */

                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }

            public static class Day20170501Bean {
                /**
                 * temperature : 20℃~24℃
                 * weather : 中雨转小雨
                 * weather_id : {"fa":"08","fb":"07"}
                 * wind : 南风微风
                 * week : 星期一
                 * date : 20170501
                 */

                private String temperature;
                private String weather;
                private WeatherIdBeanXXXX weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBeanXXXX getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBeanXXXX weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBeanXXXX {
                    /**
                     * fa : 08
                     * fb : 07
                     */

                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }

            public static class Day20170502Bean {
                /**
                 * temperature : 22℃~29℃
                 * weather : 阵雨转多云
                 * weather_id : {"fa":"03","fb":"01"}
                 * wind : 南风微风
                 * week : 星期二
                 * date : 20170502
                 */

                private String temperature;
                private String weather;
                private WeatherIdBeanXXXXX weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBeanXXXXX getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBeanXXXXX weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBeanXXXXX {
                    /**
                     * fa : 03
                     * fb : 01
                     */

                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }

            public static class Day20170503Bean {
                /**
                 * temperature : 16℃~27℃
                 * weather : 晴
                 * weather_id : {"fa":"00","fb":"00"}
                 * wind : 北风微风
                 * week : 星期三
                 * date : 20170503
                 */

                private String temperature;
                private String weather;
                private WeatherIdBeanXXXXXX weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBeanXXXXXX getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBeanXXXXXX weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBeanXXXXXX {
                    /**
                     * fa : 00
                     * fb : 00
                     */

                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }

            public static class Day20170504Bean {
                /**
                 * temperature : 20℃~24℃
                 * weather : 中雨转小雨
                 * weather_id : {"fa":"08","fb":"07"}
                 * wind : 南风微风
                 * week : 星期四
                 * date : 20170504
                 */

                private String temperature;
                private String weather;
                private WeatherIdBeanXXXXXXX weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBeanXXXXXXX getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBeanXXXXXXX weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBeanXXXXXXX {
                    /**
                     * fa : 08
                     * fb : 07
                     */

                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }
        }
    }
}
