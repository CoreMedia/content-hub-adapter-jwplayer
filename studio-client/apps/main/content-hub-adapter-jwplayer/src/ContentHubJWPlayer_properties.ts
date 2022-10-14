import SvgIconUtil from "@coremedia/studio-client.base-models/util/SvgIconUtil";
import CoreIcons_properties from "@coremedia/studio-client.core-icons/CoreIcons_properties";
import icon from "./icons/jwplayer_16.svg";

interface ContentHubJWPlayer_properties {
  adapter_type_jwplayer_name: string;
  adapter_type_jwplayer_icon: string;
  folder_type_video_name: string;
  folder_type_video_icon: string;
  item_type_audio_name: string;
  item_type_audio_icon: string;
  item_type_video_name: string;
  item_type_video_icon: string;
  item_type_playlist_name: string;
  item_type_playlist_icon: string;
  stats_sectionName: string;
  dimensions_sectionItemKey: string;
  duration_sectionItemKey: string;
  hosting_type_sectionItemKey: string;
  mime_type_sectionItemKey: string;
  dashboard_link_sectionItemKey: string;
}

/**
 * Singleton for the current user Locale's instance of ResourceBundle "ContentHubJWPlayer".
 * @see ContentHubJWPlayer_properties
 */
const ContentHubJWPlayer_properties: ContentHubJWPlayer_properties = {
  adapter_type_jwplayer_name: "JWP",
  adapter_type_jwplayer_icon: SvgIconUtil.getIconStyleClassForSvgIcon(icon),
  folder_type_video_name: "Videos",
  folder_type_video_icon: CoreIcons_properties.personal_folder_video,
  item_type_audio_name: "Audio",
  item_type_audio_icon: CoreIcons_properties.type_asset_audio,
  item_type_video_name: "Video",
  item_type_video_icon: CoreIcons_properties.type_asset_video,
  item_type_playlist_name: "Playlist",
  item_type_playlist_icon: CoreIcons_properties.bulleted_list,
  stats_sectionName: "Stats",
  dimensions_sectionItemKey: "Dimensions",
  duration_sectionItemKey: "Duration",
  hosting_type_sectionItemKey: "Hosting Type",
  mime_type_sectionItemKey: "Mime-Type",
  dashboard_link_sectionItemKey: "Dashboard Link"
};

export default ContentHubJWPlayer_properties;
