package seek.starter.starter.mq.rabbitmq.starter.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DelayPropertiesEnum {
    Level_One_One_Second("one",1000),
    Level_Two_Five_Seconds("two",5*1000),
    Level_Three_Ten_Seconds("three",10*1000),
    Level_Four_Fifteen_Seconds("four",15*1000),
    Level_Five_Thirty_Seconds("five",30*1000),
    Level_Six_One_Minute("six",60*1000),
    Level_Seven_Five_Minutes("seven",300*1000),
    Level_Eight_Ten_Minutes("eight",600*1000),
    Level_Nine_Thirty_Minutes("nine",1800*1000),
    Level_Ten_One_Hour("ten",3600*1000),
    ;

    public String levelKey;
    public long millisSeconds;
    public static final String RedeliverPropertiesDestination="${"+
            PackageEnum.Full_Package_Prefix+"config.delay.redeliver-binding.queue.queue-name}";
}
