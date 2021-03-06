/*
 *  Copyright 2019 Arcus Project.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package arcus.app.common.banners;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import arcus.app.R;
import arcus.app.common.banners.core.AbstractBanner;

public class TextOnlyBanner extends AbstractBanner {
    private String text = null;

    public TextOnlyBanner(String textToDisplay) {
        super(R.layout.text_only_banner);
        this.text = textToDisplay;
    }

    @Override public View getBannerView(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getViewResourceId(), parent, false);
        if (view == null) {
            return null;
        }

        TextView textView = (TextView) view.findViewById(R.id.text_place_holder);
        if (textView == null) {
            return view;
        }

        textView.setText(text);

        return view;
    }
}
