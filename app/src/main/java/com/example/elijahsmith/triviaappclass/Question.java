package com.example.elijahsmith.triviaappclass;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable{
    private String correctAnswer;
    private String wronganswer;
    private String title;
    private String wronganswer2;
    private String wronganser3;

    public Question(String correctAnswer, String wronganswer, String title, String wronganswer2, String wronganser3) {
        this.correctAnswer = correctAnswer;
        this.wronganswer = wronganswer;
        this.title = title;
        this.wronganswer2 = wronganswer2;
        this.wronganser3 = wronganser3;
    }

    public Question() {
    }

    protected Question(Parcel in) {
        correctAnswer = in.readString();
        wronganswer = in.readString();
        title = in.readString();
        wronganswer2 = in.readString();
        wronganser3 = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getWronganswer() {
        return wronganswer;
    }

    public String getTitle() {
        return title;
    }

    public String getWronganswer2() {
        return wronganswer2;
    }

    public String getWronganser3() {
        return wronganser3;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(correctAnswer);
        dest.writeString(wronganswer);
        dest.writeString(title);
        dest.writeString(wronganswer2);
        dest.writeString(wronganser3);
    }

}



