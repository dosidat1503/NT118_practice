package com.example.Phan1Bai23;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab3main.R;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private Context context;
    private List<Student> studentList;
    private OnStudentListener onStudentListener;

    public StudentAdapter(Context context, List<Student> studentList, OnStudentListener onStudentListener) {
        this.context = context;
        this.studentList = studentList;
        this.onStudentListener = onStudentListener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phan1bai23_student_item, parent, false);
        return new StudentViewHolder(view, onStudentListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.tvMSSV.setText(String.valueOf(student.getMssv()));
        holder.tvHoTen.setText(student.getName());
        holder.tvLop.setText(student.getLop());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvMSSV, tvHoTen, tvLop;
        OnStudentListener onStudentListener;

        public StudentViewHolder(@NonNull View itemView, OnStudentListener onStudentListener) {
            super(itemView);
            tvMSSV = itemView.findViewById(R.id.tvMSSV);
            tvHoTen = itemView.findViewById(R.id.tvHoTen);
            tvLop = itemView.findViewById(R.id.tvLop);
            this.onStudentListener = onStudentListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onStudentListener.onStudentClick(getAdapterPosition());
        }
    }

    public interface OnStudentListener {
        void onStudentClick(int position);
    }
}
