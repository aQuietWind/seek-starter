package seek.starter.starter.mq.rabbitmq.starter.configuration;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import seek.starter.starter.mq.rabbitmq.starter.enums.DelayPropertiesEnum;
import seek.starter.starter.mq.rabbitmq.starter.enums.PackageEnum;
import seek.starter.starter.support.config.object.mq.rabbitmq.BindingData;
import seek.starter.starter.support.config.object.mq.rabbitmq.ExchangeData;
import seek.starter.starter.support.config.object.mq.rabbitmq.QueueData;

@ConfigurationProperties(PackageEnum.Full_Package_Prefix +"config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertiesConfig {
    private ExchangeData errorExchange=new ExchangeData("ex.error",null);
    private String converterPackage="seek.util";
    private DelayProperties delay=new DelayProperties();

    @Data
    @NoArgsConstructor
    public static class DelayProperties{
        private String delayQueueNamePrefix="q.delay.dlq.";
        private ExchangeData delayExchange=new ExchangeData("ex.delay",ExchangeData.Direct);
        private BindingData redeliver =new BindingData(delayExchange,new QueueData("q.redeliver"),"redeliver");
        private BindingData levelOne=new BindingData(
                delayExchange
                ,new QueueData(delayQueueNamePrefix+"one",DelayPropertiesEnum.Level_One_One_Second.millisSeconds, redeliver)
                ,DelayPropertiesEnum.Level_One_One_Second.levelKey);
        private BindingData levelTwo=new BindingData(
                delayExchange
                ,new QueueData(delayQueueNamePrefix+"two",DelayPropertiesEnum.Level_Two_Five_Seconds.millisSeconds, redeliver)
                ,DelayPropertiesEnum.Level_Two_Five_Seconds.levelKey);
        private BindingData levelThree=new BindingData(
                delayExchange
                ,new QueueData(delayQueueNamePrefix+"three",DelayPropertiesEnum.Level_Three_Ten_Seconds.millisSeconds, redeliver)
                ,DelayPropertiesEnum.Level_Three_Ten_Seconds.levelKey);
        private BindingData levelFour=new BindingData(
                delayExchange
                ,new QueueData(delayQueueNamePrefix+"four",DelayPropertiesEnum.Level_Four_Fifteen_Seconds.millisSeconds, redeliver)
                ,DelayPropertiesEnum.Level_Four_Fifteen_Seconds.levelKey);
        private BindingData levelFive=new BindingData(
                delayExchange
                ,new QueueData(delayQueueNamePrefix+"five",DelayPropertiesEnum.Level_Five_Thirty_Seconds.millisSeconds, redeliver)
                ,DelayPropertiesEnum.Level_Five_Thirty_Seconds.levelKey);
        private BindingData levelSix=new BindingData(
                delayExchange
                ,new QueueData(delayQueueNamePrefix+"six",DelayPropertiesEnum.Level_Six_One_Minute.millisSeconds, redeliver)
                ,DelayPropertiesEnum.Level_Six_One_Minute.levelKey);
        private BindingData levelSeven=new BindingData(
                delayExchange
                ,new QueueData(delayQueueNamePrefix+"seven",DelayPropertiesEnum.Level_Seven_Five_Minutes.millisSeconds, redeliver)
                ,DelayPropertiesEnum.Level_Seven_Five_Minutes.levelKey);
        private BindingData levelEight=new BindingData(
                delayExchange
                ,new QueueData(delayQueueNamePrefix+"eight",DelayPropertiesEnum.Level_Eight_Ten_Minutes.millisSeconds, redeliver)
                ,DelayPropertiesEnum.Level_Eight_Ten_Minutes.levelKey);
        private BindingData levelNine=new BindingData(
                delayExchange
                ,new QueueData(delayQueueNamePrefix+"nine",DelayPropertiesEnum.Level_Nine_Thirty_Minutes.millisSeconds, redeliver)
                ,DelayPropertiesEnum.Level_Nine_Thirty_Minutes.levelKey);
        private BindingData levelTen=new BindingData(
                delayExchange
                ,new QueueData(delayQueueNamePrefix+"ten",DelayPropertiesEnum.Level_Ten_One_Hour.millisSeconds, redeliver)
                ,DelayPropertiesEnum.Level_Ten_One_Hour.levelKey);
    }
}
