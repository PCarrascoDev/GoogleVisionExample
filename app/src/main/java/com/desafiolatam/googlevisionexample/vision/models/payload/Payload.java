package com.desafiolatam.googlevisionexample.vision.models.payload;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class Payload {
    private Request[] requests = new Request[1];

    public Payload(String uri) {
        requests[0] = new Request(uri);
    }

    public Payload(Bitmap imageBitmap) {


        requests[0] = new Request(imageBitmap);
    }

    public Payload addRequestFeature(String feature, int maxResults)
    {
        requests[0].addFeature(new Request.Feature(maxResults, feature));
        return this;
    }

    private static class Request {
        private Image image;
        private Feature[] features = new Feature[1];

        Request(String imageSrc) {
            image = new Image(imageSrc);
        }

        Request(Bitmap imageBitmap) {
            image = new Image(imageBitmap);
        }

        void addFeature(Feature feature)
        {
            if (features.length <= 1)
            {
                features[0] = feature;
            }
            else
            {
                Feature[] auxFeatures = new Feature[features.length + 1];
                for (int i = 0; i < features.length; i++)
                {
                    auxFeatures[i] = features[i];
                }
                auxFeatures[features.length] = feature;

                features = auxFeatures;
            }
        }

        static class Image {
            private HttpSource source;
            private String content;

            Image(Bitmap bitmap) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] bytes = byteArrayOutputStream.toByteArray();
                content = Base64.encodeToString(bytes, Base64.DEFAULT);
            }

            Image(String imageSrc) {
                source = new HttpSource(imageSrc);
            }

            static class HttpSource {
                private String imageUri;

                HttpSource(String imageUri) {
                    this.imageUri = imageUri;
                }
            }
        }

        static class Feature {
            private int maxResults;
            private String type;
            Feature(int maxResults, String type) {
                this.maxResults = maxResults;
                this.type = type;
            }
        }
    }
}
