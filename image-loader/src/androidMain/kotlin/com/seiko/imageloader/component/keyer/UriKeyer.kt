package com.seiko.imageloader.component.keyer

import android.content.ContentResolver.SCHEME_ANDROID_RESOURCE
import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import com.seiko.imageloader.request.Options

internal class UriKeyer(private val context: Context) : Keyer<Uri> {
    override fun key(data: Uri, options: Options): String {
        // 'android.resource' uris can change if night mode is enabled/disabled.
        return if (data.scheme == SCHEME_ANDROID_RESOURCE) {
            "$data-${context.resources.configuration.nightMode}"
        } else {
            data.toString()
        }
    }

    private val Configuration.nightMode: Int
        get() = uiMode and Configuration.UI_MODE_NIGHT_MASK
}
