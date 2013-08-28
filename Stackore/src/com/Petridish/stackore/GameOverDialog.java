/**
 * 
 */
package com.Petridish.stackore;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * @author Cody
 * 
 */
public class GameOverDialog extends DialogFragment {

	public interface DialogListener {
		public void onDialogPositiveClick(DialogFragment dialog);
	}

	DialogListener mListener;

	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setMessage("Game Over!").setPositiveButton("Back to Main Menu",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						mListener.onDialogPositiveClick(GameOverDialog.this);
					}
				});

		// Create the AlertDialog object and return it
		return builder.create();
	}
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (DialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement DialogListener");
        }
    }
}
