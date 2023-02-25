/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 24/02/2023
 * Time: 16:20
 *
 * Project: csci205_labs
 * Package: lab08
 * Class: ManagerException
 *
 * Description:
 *
 * *****************************************/
package lab08;


/**
 * Checked exception representing any issues that might arise from the Manager
 * class
 */
class ManagerException extends Exception {
    public ManagerException(String message) {
        super(message);
    }
}

