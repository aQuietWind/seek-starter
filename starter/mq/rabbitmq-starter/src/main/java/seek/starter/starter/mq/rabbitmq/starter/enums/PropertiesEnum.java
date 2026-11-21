package seek.starter.starter.mq.rabbitmq.starter.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PropertiesEnum {
    One_Second(1,1),
    Five_Seconds(2,5),
    Ten_Seconds(3,10),
    Fifteen_Seconds(4,15),
    Thirty_Seconds(5,30),
    One_Minute(6,60),
    Five_Minutes(7,300),
    Ten_Minutes(8,300),
    Thirty_Minutes(9,1800),
    One_Hour(10,3600),
    Two_Hours(11,3600),
    ;

    public int level;
    public int seconds;
}
