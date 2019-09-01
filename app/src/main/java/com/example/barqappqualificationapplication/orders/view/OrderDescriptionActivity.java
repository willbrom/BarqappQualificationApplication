package com.example.barqappqualificationapplication.orders.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.barqappqualificationapplication.R;
import com.example.barqappqualificationapplication.chat.view.ChatActivity;

public class OrderDescriptionActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private ImageButton attachInvoiceImageButton;
    private EditText invoiceEditText;
    private TextView orderPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_description);

        attachInvoiceImageButton = findViewById(R.id.attach_invoice_imageButton);
        invoiceEditText = findViewById(R.id.invoice_grand_total_editText);
        orderPrice = findViewById(R.id.order_price);

        invoiceEditText.setFocusableInTouchMode(false);
        invoiceEditText.setFocusable(false);
        invoiceEditText.setFocusableInTouchMode(true);
        invoiceEditText.setFocusable(true);
    }

    public void onClickAttachInvoice(View view) {
        dispatchTakePictureIntent();
    }

    public void onClickUpdateOrder(View view) {
        String invoiceText = invoiceEditText.getText().toString();
        orderPrice.setText(invoiceText);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            attachInvoiceImageButton.setImageBitmap(imageBitmap);
        }
    }

    public void onClickChat(View view) {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }
}
