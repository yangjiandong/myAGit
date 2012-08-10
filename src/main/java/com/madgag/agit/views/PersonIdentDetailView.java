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

import static com.madgag.android.lazydrawables.gravatar.Gravatars.gravatarIdFor;
import static java.text.DateFormat.FULL;
import static java.text.DateFormat.getDateTimeInstance;
import static roboguice.RoboGuice.getInjector;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.madgag.agit.R;
import com.madgag.android.lazydrawables.ImageSession;

import org.eclipse.jgit.lib.PersonIdent;

public class PersonIdentDetailView extends FrameLayout {

    private static final String TAG = "PIDV";

    private PersonIdent ident;
    private final ImageView avatarView;
    private final TextView nameView, whenView;

    @Inject
    ImageSession avatarSession;

    public PersonIdentDetailView(Context context) {
        super(context);
        getInjector(context).injectMembers(this);
        LayoutInflater.from(context).inflate(R.layout.person_ident_detail_view, this);

        avatarView = (ImageView) findViewById(R.id.person_ident_avatar);
        nameView = (TextView) findViewById(R.id.person_ident_name);
        whenView = (TextView) findViewById(R.id.person_ident_when);
    }


    public void setIdent(String title, PersonIdent ident) {
        this.ident = ident;
        Drawable avatar = avatarSession.get(gravatarIdFor(ident.getEmailAddress()));
        avatarView.setImageDrawable(avatar);
        nameView.setText(ident.getName() + " " + ident.getEmailAddress());
        java.text.DateFormat dateFormat = getDateTimeInstance(FULL, FULL);

        String dateString = dateFormat.format(ident.getWhen());
        whenView.setText(dateString);
    }

    public PersonIdent getIdent() {
        return ident;
    }

    public void setIdent(PersonIdent ident) {
        this.ident = ident;
    }
}
