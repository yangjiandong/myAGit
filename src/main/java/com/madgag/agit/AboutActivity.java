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

package com.madgag.agit;

import static com.madgag.agit.R.string.about_activity_title;
import static com.madgag.android.ActionBarUtil.homewardsWith;

import android.content.Intent;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.MenuItem;

public class AboutActivity extends MarkdownActivityBase {

    @Override
    protected String markdownFile() {
        return "CREDITS.markdown";
    }

    @Override
    protected void configureActionBar(ActionBar actionBar) {
        actionBar.setTitle(about_activity_title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return homewardsWith(this, new Intent(this, DashboardActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}