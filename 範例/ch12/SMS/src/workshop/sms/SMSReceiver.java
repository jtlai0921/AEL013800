package workshop.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
	    // 使用Toast顯示所接收到的SMS內容
		if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
			Bundle bundle = intent.getExtras();
			Object[] objArray = (Object[]) bundle.get("pdus");
			SmsMessage[] messages = new SmsMessage[objArray.length];
			for (int i = 0; i < objArray.length; i++) {
				messages[i] = SmsMessage.createFromPdu((byte[]) objArray[i]);
			}
			StringBuilder sb = new StringBuilder();
			for (SmsMessage currentMessage : messages){
				sb.append("Receive a SMS from:");
				sb.append(currentMessage.getDisplayOriginatingAddress());
				sb.append("\nSMS content:\n");
				sb.append(currentMessage.getDisplayMessageBody());
			}
			Toast.makeText(context, sb.toString(), Toast.LENGTH_SHORT).show();

		}
	}
}
