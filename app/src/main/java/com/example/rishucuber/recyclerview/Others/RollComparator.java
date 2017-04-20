package com.example.rishucuber.recyclerview.Others;



    import com.example.rishucuber.recyclerview.Model.StudentInformation;

    import java.util.Comparator;



    public class RollComparator implements Comparator<StudentInformation> {
        @Override
        public int compare(StudentInformation o1, StudentInformation o2) {
            if (o1.getmRollno() < o2.getmRollno())
                return -1;
            else if (o1.getmRollno() == o2.getmRollno())
                return 0;
            else
                return 1;
        }
    }
