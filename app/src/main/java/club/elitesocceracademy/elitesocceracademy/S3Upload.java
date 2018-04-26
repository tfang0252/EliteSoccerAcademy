package club.elitesocceracademy.elitesocceracademy;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class S3Upload {

    AmazonS3 s3Client;
    String bucket = "esa-json";
    File uploadToS3 = new File("/storage/emulated/0/TeamRoster.csv");
    File downloadFromS3 = new File("/storage/emulated/0/Pictures/IMG_20180411_131302.jpg");
    TransferUtility transferUtility;
    List<String> listing;
    String FileName;
    Context context;

    S3Upload(Context context, String FileName, View view){
        this.context=context;
        this.FileName = FileName;
        uploadToS3 = new File("/storage/emulated/0/"+FileName);

        s3credentialsProvider();
        // callback method to call the setTransferUtility method
        setTransferUtility();
        uploadFileToS3(view);
    }

    public void s3credentialsProvider(){

        // Initialize the AWS Credential
        CognitoCachingCredentialsProvider cognitoCachingCredentialsProvider =
                new CognitoCachingCredentialsProvider(
                        context,
                        "us-east-1:bef4e01d-91af-4a43-934b-99ce08db3e97", // Identity Pool ID
                        Regions.US_EAST_1 // Region
                );
        createAmazonS3Client(cognitoCachingCredentialsProvider);
    }

    /**
     *  Create a AmazonS3Client constructor and pass the credentialsProvider.
     * @param credentialsProvider
     */
    public void createAmazonS3Client(CognitoCachingCredentialsProvider
                                             credentialsProvider){

        // Create an S3 client
        s3Client = new AmazonS3Client(credentialsProvider);

        // Set the region of your S3 bucket
        s3Client.setRegion(Region.getRegion(Regions.US_EAST_1));
    }

    public void setTransferUtility(){

        transferUtility = new TransferUtility(s3Client, context);
    }

    /**
     * This method is used to upload the file to S3 by using TransferUtility class
     * @param view
     */
    public void uploadFileToS3(View view){
//        try {
//            s3Client.putObject(new PutObjectRequest(
//                    "elite-soccer-academy", "IMG_20180411_131302.jpg", uploadToS3));
//        }catch (AmazonServiceException ase) {
//            System.out.println("Caught an AmazonServiceException, which " +
//                    "means your request made it " +
//                    "to Amazon S3, but was rejected with an error response" +
//                    " for some reason.");
//            System.out.println("Error Message:    " + ase.getMessage());
//            System.out.println("HTTP Status Code: " + ase.getStatusCode());
//            System.out.println("AWS Error Code:   " + ase.getErrorCode());
//            System.out.println("Error Type:       " + ase.getErrorType());
//            System.out.println("Request ID:       " + ase.getRequestId());
//        } catch (AmazonClientException ace) {
//            System.out.println("Caught an AmazonClientException, which " +
//                    "means the client encountered " +
//                    "an internal error while trying to " +
//                    "communicate with S3, " +
//                    "such as not being able to access the network.");
//            System.out.println("Error Message: " + ace.getMessage());
//        }


        TransferObserver transferObserver = transferUtility.upload(
                bucket,     /* The bucket to upload to */
                FileName,    /* The key for the uploaded object */
                uploadToS3       /* The file where the data to upload exists */
        );

        transferObserverListener(transferObserver);
    }

    /**
     *  This method is used to Download the file to S3 by using transferUtility class
     * @param view
     **/
    public void downloadFileFromS3(View view){

        TransferObserver transferObserver = transferUtility.download(
                bucket,     /* The bucket to download from */
                FileName,    /* The key for the object to download */
                downloadFromS3        /* The file to download the object to */
        );
        transferObserverListener(transferObserver);
    }

    public void fetchFileFromS3(View view){

        // Get List of files from S3 Bucket
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {

                try {
                    Looper.prepare();
                    listing = getObjectNamesForBucket(bucket, s3Client);

                    for (int i=0; i< listing.size(); i++){

                    }
                    Looper.loop();
                    // Log.e("tag", "listing "+ listing);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    Log.e("tag", "Exception found while listing "+ e);
                }

            }
        });
        thread.start();
    }

    /**
     * @desc This method is used to return list of files name from S3 Bucket
     * @param bucket
     * @param s3Client
     * @return object with list of files
     */
    private List<String> getObjectNamesForBucket(String bucket, AmazonS3 s3Client) {
        ObjectListing objects=s3Client.listObjects(bucket);
        List<String> objectNames=new ArrayList<String>(objects.getObjectSummaries().size());
        Iterator<S3ObjectSummary> iterator=objects.getObjectSummaries().iterator();
        while (iterator.hasNext()) {
            objectNames.add(iterator.next().getKey());
        }
        while (objects.isTruncated()) {
            objects=s3Client.listNextBatchOfObjects(objects);
            iterator=objects.getObjectSummaries().iterator();
            while (iterator.hasNext()) {
                objectNames.add(iterator.next().getKey());
            }
        }
        return objectNames;
    }

    /**
     * This is listener method of the TransferObserver
     * Within this listener method, we get status of uploading and downloading file,
     * to display percentage of the part of file to be uploaded or downloaded to S3
     * It displays an error, when there is a problem in  uploading or downloading file to or from S3.
     * @param transferObserver
     */

    public void transferObserverListener(TransferObserver transferObserver){

        transferObserver.setTransferListener(new TransferListener(){

            @Override
            public void onStateChanged(int id, TransferState state) {

            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                int percentage = (int) (bytesCurrent/bytesTotal * 100);

            }

            @Override
            public void onError(int id, Exception ex) {
                Log.e("error","error");
            }

        });
    }


}
