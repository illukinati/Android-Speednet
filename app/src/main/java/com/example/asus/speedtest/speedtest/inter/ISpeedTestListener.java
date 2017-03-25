/*
 * The MIT License (MIT)
 * <p/>
 * Copyright (c) 2016 Bertrand Martel
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.example.asus.speedtest.speedtest.inter;

import com.example.asus.speedtest.speedtest.SpeedTestReport;
import com.example.asus.speedtest.speedtest.model.SpeedTestError;

/**
 * Listener for speed examples output results.
 * <p/>
 * <ul>
 * <li>monitor download process result with transfer rate in bit/s and octet/s</li>
 * <li>monitor download progress</li>
 * <li>monitor upload process result with transfer rate in bit/s and octet/s</li>
 * <li>monitor upload progress</li>
 * </ul>
 *
 * @author Bertrand Martel
 */

public interface ISpeedTestListener {

    /**
     * monitor download process result with transfer rate in bit/s and octet/s.
     *
     * @param report download speed test report
     */
    void onDownloadFinished(SpeedTestReport report);

    /**
     * monitor download progress.
     *
     * @param percent % of progress
     * @param report  current speed test download report
     */
    void onDownloadProgress(float percent, SpeedTestReport report);

    /**
     * Error catch for download process.
     *
     * @param speedTestError error enum
     * @param errorMessage   error message
     */
    void onDownloadError(SpeedTestError speedTestError, String errorMessage);

    /**
     * monitor upload process result with transfer rate in bit/s and octet/s.
     *
     * @param report upload speed test report
     */
    void onUploadFinished(SpeedTestReport report);

    /**
     * Error catch for upload process.
     *
     * @param speedTestError error enum
     * @param errorMessage   error message
     */
    void onUploadError(SpeedTestError speedTestError, String errorMessage);

    /**
     * monitor upload progress.
     *
     * @param percent % of progress
     * @param report  current speed test upload report
     */
    void onUploadProgress(float percent, SpeedTestReport report);

    /**
     * called when speed test is interrupted.
     */
    void onInterruption();

}
