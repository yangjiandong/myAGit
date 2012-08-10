/*
 * Copyright (c) 2011 Roberto Tyley
 *
 * This file is part of 'Agit' - an Android Git client.
 *
 * Agit is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Agit is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.madgag.agit.views;

import static com.madgag.agit.R.id.osv_object_id_text;
import static com.madgag.agit.R.id.osv_object_type_icon;
import static com.madgag.agit.R.id.osv_object_type_text;
import static com.madgag.agit.R.id.osv_type_specific_data_frame;
import static com.madgag.agit.R.layout.object_summary_view;
import static com.madgag.agit.git.GitObjects.evaluate;
import static roboguice.RoboGuice.getBaseApplicationInjector;
import android.app.Application;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.madgag.agit.git.GitObjectFunction;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevBlob;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevObject;
import org.eclipse.jgit.revwalk.RevTag;
import org.eclipse.jgit.revwalk.RevTree;

public class ObjectSummaryView extends LinearLayout {

    private static final String TAG = "OSV";

    ImageView objectTypeIcon;
    ObjectIdView objectIdView;
    TextView objectTypeTextView;
    ViewGroup typeSpecificFrame;

    public ObjectSummaryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        LayoutInflater.from(context).inflate(object_summary_view, this);
        objectTypeIcon = (ImageView) findViewById(osv_object_type_icon);
        objectIdView = (ObjectIdView) findViewById(osv_object_id_text);
        objectTypeTextView = (TextView) findViewById(osv_object_type_text);
        typeSpecificFrame = (ViewGroup) findViewById(osv_type_specific_data_frame);
    }

    public void setObject(RevObject gitObject, Repository repository) {
        OSV osv = evaluate(gitObject, new GitObjectFunction<OSV<?>>() {
            public OSV<?> apply(RevCommit commit) {
                return new CommitSummaryView();
            }

            public OSV<?> apply(RevTree tree) {
                return new TreeSummaryView();
            }

            public OSV<?> apply(RevBlob blob) {
                return new BlobSummaryView();
            }

            public OSV<?> apply(RevTag tag) {
                return new TagSummaryView();
            }
        });
        objectTypeIcon.setImageResource(osv.iconId());
        objectTypeTextView.setText(osv.getTypeName());
        objectIdView.setObjectId(gitObject);

        typeSpecificFrame.removeAllViews();
        LayoutInflater.from(getContext()).inflate(osv.layoutId(), typeSpecificFrame);
        Log.d(TAG, "About to set type-specific info for gitObject=" + gitObject);
        osv.setObject(gitObject, typeSpecificFrame, repository);
    }
}
