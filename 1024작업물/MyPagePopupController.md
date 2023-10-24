## MyPagePopupController.cs

```
using UnityEngine;

public class MyPagePopupController : MonoBehaviour
{
    [SerializeField] private GameObject myPagePopup;

    public void PopupOpen()
    {
        myPagePopup.SetActive(true);
    }

    public void PopupClose()
    {
        myPagePopup.SetActive(false);
    }
}

```
