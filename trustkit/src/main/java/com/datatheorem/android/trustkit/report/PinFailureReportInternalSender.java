package com.datatheorem.android.trustkit.report;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import java.net.URL;


/**
 * PinFailureReportInternalSender send a local broadcast message with the report
 */
class PinFailureReportInternalSender{
    public static final String TRUSTKIT_INTENT_SERVER_HOSTNAME_KEY =
            "TRUSTKIT_INTENT_SERVER_HOSTNAME_KEY";
    public static final String TRUSTKIT_INTENT_VALIDATION_DURATION_KEY =
            "TRUSTKIT_INTENT_VALIDATION_DURATION_KEY";
    public static final String TRUSTKIT_INTENT_NOTED_HOSTNAME_KEY =
            "TRUSTKIT_INTENT_NOTED_HOSTNAME_KEY";
    public static final String TRUSTKIT_INTENT_CERTIFICATE_CHAIN_KEY =
            "TRUSTKIT_INTENT_CERTIFICATE_CHAIN_KEY";
    public static final String TRUSTKIT_INTENT_VALIDATION_RESULT_KEY =
            "TRUSTKIT_INTENT_VALIDATION_RESULT_KEY";

    private String broadcastIdentifier;
    private Context applicationContext;

    public PinFailureReportInternalSender(Context applicationContext, String broadcastIdentifier) {
        this.applicationContext = applicationContext.getApplicationContext();
        this.broadcastIdentifier = broadcastIdentifier;
    }


    public void send(final PinFailureReport pinFailureReport) {
        Intent intent = new Intent(broadcastIdentifier);


        // userInfo:@{kTSKValidationDurationNotificationKey: @(validationDuration),
        // kTSKValidationDecisionNotificationKey: @(finalTrustDecision),
        // kTSKValidationResultNotificationKey: @(validationResult),
        // kTSKValidationCertificateChainNotificationKey: certificateChain,
        // kTSKValidationNotedHostnameNotificationKey: notedHostname,
        // kTSKValidationServerHostnameNotificationKey: serverHostname}];
//        reportBundle.putSerializable("report", pinFailureReport);

        intent.putExtra(TRUSTKIT_INTENT_SERVER_HOSTNAME_KEY, pinFailureReport.getServerHostname());
        //todo(jb) add validation duration
//        intent.putExtra(TRUSTKIT_INTENT_VALIDATION_DURATION_KEY, 0);
        intent.putExtra(TRUSTKIT_INTENT_NOTED_HOSTNAME_KEY, pinFailureReport.getNotedHostname());
        intent.putExtra(TRUSTKIT_INTENT_CERTIFICATE_CHAIN_KEY, pinFailureReport.getValidatedCertificateChain());
        intent.putExtra(TRUSTKIT_INTENT_VALIDATION_RESULT_KEY, pinFailureReport.getValidationResult());
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent);

    }
}
