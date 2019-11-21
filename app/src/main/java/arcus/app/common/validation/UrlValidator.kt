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
package arcus.app.common.validation

import android.util.Patterns
import android.widget.EditText
import androidx.annotation.StringRes

import arcus.app.R

class UrlValidator(
        private val input: EditText,
        private val errorText: String
) : InputValidator {
    private val urlText: String = input.text?.toString().orEmpty()

    @JvmOverloads
    constructor(
            input: EditText,
            @StringRes errorTextResource: Int = R.string.invalid_url
    ) : this(input, input.context.getString(errorTextResource))

    override fun isValid(): Boolean {
        if (urlText.isBlank() || !Patterns.WEB_URL.matcher(urlText).matches()) {
            input.error = errorText
            return false
        }

        return true
    }
}
