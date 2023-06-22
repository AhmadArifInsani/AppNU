package com.example.login.adapter;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;
import com.example.login.model.AdministrasiModel;
import static android.os.Environment.DIRECTORY_DOWNLOADS;

import java.util.ArrayList;
import java.util.List;

public class AdapterAdministrasi extends RecyclerView.Adapter<AdapterAdministrasi.ViewHolder> {
    Context context;
    ArrayList<AdministrasiModel> models;

    public AdapterAdministrasi(Context context, ArrayList<AdministrasiModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public AdapterAdministrasi.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_administrasi, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAdministrasi.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.namaFile.setText(models.get(position).getName());
        holder.btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // downloadFile(holder.namaFile.getContext(), models.get(position).getName(), ".docx", DIRECTORY_DOWNLOADS, models.get(position).getUrlPdf());
            }
        });
    }
   /* public void downloadFile(Context context, String nama, String fileExtension, String destinationDirectory, String url){
        DownloadManager downloadManager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, nama + fileExtension);
        downloadManager.enqueue(request);
    }*/

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaFile;
        Button btnDownload;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaFile = itemView.findViewById(R.id.text_aswaja);
            btnDownload = itemView.findViewById(R.id.btn_aswaja);
        }
    }
}
