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
package arcus.app.device.details;

import androidx.annotation.NonNull;
import android.widget.TextView;

import com.iris.client.capability.DeviceConnection;
import com.iris.client.model.DeviceModel;
import arcus.app.R;
import arcus.app.common.fragments.IShowedFragment;

import java.beans.PropertyChangeEvent;

public class AccessoryFragment extends ArcusProductFragment implements IShowedFragment {
    private TextView connectionText;

    public static AccessoryFragment newInstance() {
        return new AccessoryFragment();
    }

    @Override
    public Integer topSectionLayout() {
        return R.layout.device_top_schedule;
    }

    @Override
    public void doTopSection() {}

    @Override
    public void doStatusSection() {}

    public void onResume() {
        super.onResume();

        if (statusView == null) {
            return;
        }
        connectionText = (TextView) statusView.findViewById(R.id.status_line_text);
        updateConnectionString();
    }

    @Override
    public void propertyUpdated(@NonNull PropertyChangeEvent event) {
        switch (event.getPropertyName()) {
            case DeviceConnection.ATTR_STATE:
                updateConnectionString();
                break;

            default: // No - Op
                break;
        }
    }

    protected void updateConnectionString() {
        DeviceModel model = getDeviceModel();
        String text = "";
        if (model != null) {
            if(model.get(DeviceConnection.ATTR_STATE).equals(DeviceConnection.STATE_ONLINE)) {
                text = getString(R.string.connected).toUpperCase();
            } else {
                text = getString(R.string.device_no_connection).toUpperCase();
            }
        }

        updateTextView(connectionText, text);
    }

    @Override
    public Integer statusSectionLayout() {
        return R.layout.single_line_status;
    }

    @Override
    public void onShowedFragment() {
        checkConnection();
    }
}
