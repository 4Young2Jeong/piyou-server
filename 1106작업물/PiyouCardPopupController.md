## PiyouCardPopupController.cs

```
using System;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
using TMPro;

public class PiyouCardPopupController : MonoBehaviour
{
    [SerializeField] private GameObject piyouCardPopup;
    [SerializeField] private GameObject CardObject;
    private bool isOpen;

    private void Update()
    {
        if (Input.GetMouseButtonDown(0) && this.isOpen) // 0은 왼쪽 마우스 버튼/터치를 의미
        {
            AudioManager.instance.PlaySfx(AudioManager.Sfx.BtnClick);
            PopupClose();
        }
    }

    public void PopupOpen(string piyouName)
    {
        this.isOpen = true;
        SettingCardInfo(piyouName);

        piyouCardPopup.SetActive(true);
        AudioManager.instance.PlaySfx(AudioManager.Sfx.BtnClick);
        MainScenePiyouController.Instance.SetPossibleClick(false);
    }

    public void PopupClose()
    {
        this.isOpen = false;
        piyouCardPopup.SetActive(false);
        AudioManager.instance.PlaySfx(AudioManager.Sfx.BtnClick);
        MainScenePiyouController.Instance.SetPossibleClick(true);
    }

    private void SettingCardInfo(string piyouName)
    {
        string showText = "";
        string rarityStar = "";
        foreach (string name in Enum.GetNames(typeof(Item)))
        {
            string[] nameInfo = name.Split("_");

            if (piyouName == nameInfo[1])
            {
                showText = nameInfo[0] + " 피유";
                rarityStar = nameInfo[3];
                break;
            }
        }
        CardObject.transform.Find("Name").gameObject.GetComponent<TextMeshProUGUI>().text = showText;

        GameObject CardBg = CardObject.transform.Find("Background").gameObject;
        GameObject CardImg = CardBg.transform.Find("Image").gameObject;
        GameObject CardInfo = CardObject.transform.Find("Info").gameObject;
        GameObject CardStars = CardInfo.transform.Find("Stars").gameObject;
        GameObject CardStar = CardStars.transform.Find("Star").gameObject;
        GameObject CardStarOn = CardStar.transform.Find("Star_On").gameObject;
        GameObject CardOn2 = CardStarOn.transform.Find("On2").gameObject;
        GameObject CardOn3 = CardStarOn.transform.Find("On3").gameObject;

        for (int i = 0; i < PiyouManager.Instance.GalleryImages.Length; i++)
        {
            string fullName = Enum.GetName(typeof(Item), i);
            string realName = fullName.Split("_")[1];
            Color color = PiyouManager.HexToColor(fullName.Split("_")[2]);
            if (piyouName == realName)
            {
                Image ProfileImage = PiyouManager.Instance.GalleryImages[i];

                if (ProfileImage != null)
                {
                    if (ProfileImage.transform.parent)
                    {
                        CardBg.transform.GetComponent<Image>().color = color;
                    }
                    CardImg.GetComponent<Image>().sprite = ProfileImage.sprite;

                    CardOn2.SetActive(false);
                    CardOn3.SetActive(false);

                    switch (rarityStar)
                    {
                        case "2":
                            CardOn2.SetActive(true);
                            break;
                        case "3":
                            CardOn2.SetActive(true);
                            CardOn3.SetActive(true);
                            break;
                    }
                }

                break;
            }
        }
    }
}

```
